package com.mohammadfayaz.hn.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.sources.local.source.IdsLocalSource
import com.mohammadfayaz.hn.data.sources.local.source.StoriesLocalSource
import com.mohammadfayaz.hn.data.sources.network.source.IdsNetworkSource
import com.mohammadfayaz.hn.data.sources.network.source.StoriesNetworkSource
import com.mohammadfayaz.hn.domain.DataConfig.MAX_ITEMS_LIMIT
import com.mohammadfayaz.hn.domain.DataConfig.MAX_PAGE_SIZE
import com.mohammadfayaz.hn.domain.DataConfig.PRE_FETCH_DISTANCE
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryIdModel
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.models.StoryType
import com.mohammadfayaz.hn.domain.paging.StoryPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowStoriesRepo @Inject constructor(
  private val idsNetworkSource: IdsNetworkSource,
  storiesNetworkSource: StoriesNetworkSource,
  storyLocalSource: StoriesLocalSource,
  idsLocalSource: IdsLocalSource
) : BaseStoryRepo(idsNetworkSource, storiesNetworkSource, storyLocalSource, idsLocalSource) {

  private val storyType: StoryType = StoryType.SHOW

  override suspend fun fetchStoryIds(): ApiResult<List<Int>> {

    val networkResponse = idsNetworkSource.getShowStoryIds()
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

  override suspend fun fetchItemById(id: Int): ApiResult<StoryModel> {
    return fetchItemByIdAndType(id, storyType)
  }

  override fun getPaginatedFlow(idsList: List<Int>): Flow<PagingData<StoryModel>> = Pager(
    config = PagingConfig(
      pageSize = MAX_ITEMS_LIMIT,
      maxSize = MAX_PAGE_SIZE,
      prefetchDistance = PRE_FETCH_DISTANCE,
      enablePlaceholders = false
    ),
    pagingSourceFactory = { StoryPagingSource(::fetchItemById, idsList) }
  ).flow
}
