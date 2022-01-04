package com.mohammadfayaz.hn.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.sources.local.dao.IdsDao
import com.mohammadfayaz.hn.data.sources.local.dao.StoryDao
import com.mohammadfayaz.hn.data.sources.network.ResultWrapper
import com.mohammadfayaz.hn.data.sources.network.api.HackerNewsAPI
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
  private val api: HackerNewsAPI,
  storyDao: StoryDao,
  idDao: IdsDao
) : BaseStoryRepo(api, storyDao, idDao) {

  private val storyType: StoryType = StoryType.SHOW

  override suspend fun fetchStoryIds(): ApiResult<List<Int>> {

    val networkResponse = ResultWrapper.safeApiCall {
      api.getShowStories()
    }
    val localResponse: List<StoryIdModel> = fetchIdsFromDb(storyType)

    val idsList = mutableSetOf<Int>()

    return when (
      networkResponse
    ) {
      is ResultWrapper.GenericError -> {
        if (localResponse.isNotEmpty()) {
          idsList.addAll(copyIdsIntoList(localResponse))
          ApiResult.OK(
            "Unable to fetch data from internet, fetched cached data",
            idsList.toList()
          )
        } else {
          ApiResult.ERROR(networkResponse.error + ", no data available in cached storage")
        }
      }
      ResultWrapper.NetworkError -> {
        if (localResponse.isNotEmpty()) {
          idsList.addAll(copyIdsIntoList(localResponse))
          ApiResult.OK("No internet connection, fetched cached data", idsList.toList())
        } else {
          ApiResult.NetworkError
        }
      }
      is ResultWrapper.Success -> {
        if (networkResponse.value.body() != null) {
          val list = mutableListOf<StoryIdModel>()
          val currentTimestamp = System.currentTimeMillis()
          for (i in networkResponse.value.body()!!) {
            idsList.add(i)
            list.add(StoryIdModel(i, storyType, currentTimestamp))
          }
          storeIdsInDb(list)
          ApiResult.OK("", idsList.toList())
        } else {
          ApiResult.ERROR("No data found")
        }
      }
    }
  }

  private fun copyIdsIntoList(
    fromList: List<StoryIdModel>
  ): MutableSet<Int> {
    val idsList: MutableSet<Int> = mutableSetOf()
    for (i in fromList) {
      idsList.add(i.id)
    }
    return idsList
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
