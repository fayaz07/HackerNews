package com.mohammadfayaz.hn.data.repository

import com.mohammadfayaz.hn.data.sources.local.source.IdsLocalSource
import com.mohammadfayaz.hn.data.sources.network.source.IdsNetworkSource
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryIdModel
import com.mohammadfayaz.hn.domain.models.StoryType
import com.mohammadfayaz.hn.domain.repository.IIdsRepo
import timber.log.Timber
import javax.inject.Inject

class IdsRepo @Inject constructor(
  private val idsLocalSource: IdsLocalSource,
  private val idsNetworkSource: IdsNetworkSource
) : IIdsRepo {

  override suspend fun fetchStoryIds(storyType: StoryType): ApiResult<List<Int>> {
    val networkResponse = if (AppCache.canFetch(storyType)) {
      idsNetworkSource.getIdsByType(storyType)
    } else {
      Timber.d(
        "Not fetching ids for type: $storyType " +
          "as the app just fetched ids less than 5 mins ago"
      )
      ApiResult.OK("", emptyList())
    }
    val localResponse: List<StoryIdModel> = fetchIdsFromDb(storyType)

    val idsList = mutableSetOf<Int>()

    localResponse.forEach { item ->
      idsList.add(item.id)
    }

    if (networkResponse.success && networkResponse.result != null) {
      val list = mutableListOf<StoryIdModel>()
      val currentTimestamp = System.currentTimeMillis()
      for (i in networkResponse.result) {
        idsList.add(i)
        list.add(StoryIdModel(i, storyType, currentTimestamp))
      }
      storeIdsInDb(list)
      return ApiResult.OK(networkResponse.message, idsList.toList())
    }

    return if (idsList.size == 0) {
      ApiResult.ERROR(networkResponse.message)
    } else {
      ApiResult.OK("Fetched data from cache", idsList.toList())
    }
  }

  private suspend fun fetchIdsFromDb(type: StoryType): List<StoryIdModel> {
    return idsLocalSource.getIdsByStoryType(type)
  }

  private suspend fun storeIdsInDb(list: List<StoryIdModel>) {
    idsLocalSource.putIds(list)
  }
}
