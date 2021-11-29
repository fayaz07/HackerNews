package com.mohammadfayaz.news.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mohammadfayaz.news.data.db.dao.ShowStoryDao
import com.mohammadfayaz.news.data.models.ApiResult
import com.mohammadfayaz.news.data.models.ShowStoryModel
import com.mohammadfayaz.news.domain.DataConfig
import com.mohammadfayaz.news.domain.paging.ShowStoriesPagingSource
import com.mohammadfayaz.news.network.api.HackerNewsAPI
import com.mohammadfayaz.news.network.models.response.IdsResponse
import retrofit2.Response
import javax.inject.Inject

class ShowStoriesRepo @Inject constructor(
  private val api: HackerNewsAPI,
  private val dao: ShowStoryDao
) {

  suspend fun pullTopStories(): Response<IdsResponse> {
    return api.getTopStories()
  }

  suspend fun pullShowStoryById(id: Int): Response<ShowStoryModel> {
    return api.getShowStoryById(id)
  }

  fun getPaginatedFlow(idsList: List<Int>) = Pager(
    config = PagingConfig(
      pageSize = DataConfig.MAX_ITEMS_LIMIT,
      maxSize = 500,
      enablePlaceholders = false
    ),
    pagingSourceFactory = { ShowStoriesPagingSource(::fetchItemById, idsList) }
  ).flow

  private suspend fun fetchItemById(id: Int): ApiResult<ShowStoryModel> {
    val fromLocal = fetchItemByIdFromLocalDb(id)
    if (fromLocal != null) {
      return ApiResult(data = fromLocal, success = true)
    }

    val fromNetwork = fetchItemByIdFromNetwork(id)
    return if (fromNetwork.isSuccessful) {
      storeInDb(fromNetwork.body()!!)
      ApiResult(data = fromNetwork.body()!!, success = true)
    } else {
      ApiResult(success = false, message = "", data = null)
    }
  }

  private suspend fun fetchItemByIdFromLocalDb(id: Int): ShowStoryModel? {
    return dao.getById(id)
  }

  private suspend fun fetchItemByIdFromNetwork(id: Int): Response<ShowStoryModel> {
    return api.getShowStoryById(id)
  }

  private suspend fun storeInDb(item: ShowStoryModel) {
    dao.insert(item)
  }
}
