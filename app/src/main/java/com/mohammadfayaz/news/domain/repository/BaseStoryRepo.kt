package com.mohammadfayaz.news.domain.repository

import androidx.paging.PagingData
import com.mohammadfayaz.news.data.db.dao.StoryDao
import com.mohammadfayaz.news.data.models.ApiResult
import com.mohammadfayaz.news.data.models.StoryModel
import com.mohammadfayaz.news.data.models.StoryType
import com.mohammadfayaz.news.network.api.HackerNewsAPI
import com.mohammadfayaz.news.network.models.response.IdsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import timber.log.Timber

abstract class BaseStoryRepo constructor(
  private val api: HackerNewsAPI,
  private val dao: StoryDao
) {

  abstract suspend fun fetchStories(): Response<IdsResponse>

  abstract suspend fun fetchItemById(id: Int): ApiResult<StoryModel>

  abstract fun getPaginatedFlow(idsList: List<Int>): Flow<PagingData<StoryModel>>

  protected suspend fun fetchItemByIdAndType(id: Int, type: StoryType): ApiResult<StoryModel> {
    val fromLocal = fetchItemByIdFromLocalDb(id)
    if (fromLocal != null) {
//      Timber.d("Item exists in localdb $fromLocal")
      return ApiResult(data = fromLocal, success = true)
    }

    val fromNetwork = fetchItemByIdFromNetwork(id)
    return if (fromNetwork.isSuccessful) {
      storeInDb(fromNetwork.body()!!, type)
      ApiResult(data = fromNetwork.body()!!, success = true)
    } else {
      ApiResult(success = false, message = "", data = null)
    }
  }

  private suspend fun fetchItemByIdFromLocalDb(id: Int): StoryModel? {
    return dao.getById(id)
  }

  private suspend fun fetchItemByIdFromNetwork(id: Int): Response<StoryModel> {
    return api.getStoryById(id)
  }

  private suspend fun storeInDb(item: StoryModel, type: StoryType) {
    item.storyType = type
    item.setDefaults()
//    Timber.d("Storing in localdb")
    dao.insert(item)
  }
}
