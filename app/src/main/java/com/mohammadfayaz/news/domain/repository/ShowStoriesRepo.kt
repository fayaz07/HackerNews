package com.mohammadfayaz.news.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mohammadfayaz.news.data.db.dao.StoryDao
import com.mohammadfayaz.news.data.models.ApiResult
import com.mohammadfayaz.news.data.models.StoryModel
import com.mohammadfayaz.news.data.models.StoryType
import com.mohammadfayaz.news.domain.DataConfig
import com.mohammadfayaz.news.domain.paging.StoryPagingSource
import com.mohammadfayaz.news.network.api.HackerNewsAPI
import com.mohammadfayaz.news.network.models.response.IdsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ShowStoriesRepo @Inject constructor(
  private val api: HackerNewsAPI,
  dao: StoryDao
) : BaseStoryRepo(api, dao) {

  private val storyType: StoryType = StoryType.SHOW

  override suspend fun fetchStories(): Response<IdsResponse> {
    return api.getShowStories()
  }

  override suspend fun fetchItemById(id: Int): ApiResult<StoryModel> {
    return fetchItemByIdAndType(id, storyType)
  }

  override fun getPaginatedFlow(idsList: List<Int>): Flow<PagingData<StoryModel>> = Pager(
    config = PagingConfig(
      pageSize = DataConfig.MAX_ITEMS_LIMIT,
      maxSize = idsList.size,
      enablePlaceholders = false
    ),
    pagingSourceFactory = { StoryPagingSource(::fetchItemById, idsList) }
  ).flow

}
