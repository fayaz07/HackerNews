package com.mohammadfayaz.hn.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowStoriesRepo @Inject constructor(
  private val api: HackerNewsAPI,
  dao: StoryDao
) : BaseStoryRepo(api, dao) {

  private val storyType: StoryType = StoryType.SHOW

  override suspend fun fetchStories(): ApiResult<IdsResponse> {
    return when (
      val response = ResultWrapper.safeApiCall {
        api.getShowStories()
      }
    ) {
      is ResultWrapper.GenericError -> ApiResult.ERROR(response.error)
      ResultWrapper.NetworkError -> ApiResult.NETWORK_ERROR
      is ResultWrapper.Success -> ApiResult.OK("", response.value.body()!!)
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
