package com.mohammadfayaz.hn.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.db.dao.IdsDao
import com.mohammadfayaz.hn.data.db.dao.StoryDao
import com.mohammadfayaz.hn.data.models.ApiResult
import com.mohammadfayaz.hn.data.models.StoryModel
import com.mohammadfayaz.hn.data.models.StoryType
import com.mohammadfayaz.hn.domain.DataConfig.MAX_ITEMS_LIMIT
import com.mohammadfayaz.hn.domain.DataConfig.MAX_PAGE_SIZE
import com.mohammadfayaz.hn.domain.DataConfig.PRE_FETCH_DISTANCE
import com.mohammadfayaz.hn.domain.paging.StoryPagingSource
import com.mohammadfayaz.hn.network.ResultWrapper
import com.mohammadfayaz.hn.network.api.HackerNewsAPI
import com.mohammadfayaz.hn.network.models.response.IdsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowStoriesRepo @Inject constructor(
  private val api: HackerNewsAPI,
  storyDao: StoryDao,
  idDao: IdsDao
) : BaseStoryRepo(api, storyDao, idDao) {

  private val storyType: StoryType = StoryType.SHOW

  override suspend fun fetchStoryIds(): ApiResult<IdsResponse> {

    withContext(Dispatchers.Default) {
      val idRequests = mutableListOf<Any>()
      idRequests.add(
        ResultWrapper.safeApiCall {
          api.getShowStories()
        })
      idRequests.add(fetchIdsFromDb(storyType))

      val runningTasks = idRequests.map {
        async {
          it
        }
      }
      val responses = runningTasks.awaitAll()

      
//      return when (
//        response
//      ) {
//        is ResultWrapper.GenericError -> ApiResult.ERROR(response.error)
//        ResultWrapper.NetworkError -> ApiResult.NetworkError
//        is ResultWrapper.Success -> ApiResult.OK("", response.value.body()!!)
//      }
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
