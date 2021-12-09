package com.mohammadfayaz.hn.domain.repository

import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.db.dao.StoryDao
import com.mohammadfayaz.hn.data.models.ApiResult
import com.mohammadfayaz.hn.data.models.StoryModel
import com.mohammadfayaz.hn.data.models.StoryType
import com.mohammadfayaz.hn.network.ResultWrapper
import com.mohammadfayaz.hn.network.ResultWrapper.Companion.safeApiCall
import com.mohammadfayaz.hn.network.api.HackerNewsAPI
import com.mohammadfayaz.hn.network.models.response.IdsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

abstract class BaseStoryRepo constructor(
  private val api: HackerNewsAPI,
  private val dao: StoryDao
) {

  abstract suspend fun fetchStories(): ApiResult<IdsResponse>

  abstract suspend fun fetchItemById(id: Int): ApiResult<StoryModel>

  abstract fun getPaginatedFlow(idsList: List<Int>): Flow<PagingData<StoryModel>>

  protected suspend fun fetchItemByIdAndType(id: Int, type: StoryType): ApiResult<StoryModel> {
    val fromLocal = fetchItemByIdFromLocalDb(id)
    if (fromLocal != null) {
//      Timber.d("Item exists in localdb $fromLocal")
      return ApiResult.OK(res = fromLocal)
    }

    try {

      return when (val fromNetwork = fetchItemByIdFromNetwork(id)) {
        is ResultWrapper.GenericError -> ApiResult.ERROR(fromNetwork.error)
        ResultWrapper.NetworkError -> ApiResult.NETWORK_ERROR
        is ResultWrapper.Success -> {
          storeInDb(fromNetwork.value.body()!!, type)
          ApiResult.OK(res = fromNetwork.value.body()!!)
        }
      }
    } catch (e: Exception) {
      e.printStackTrace()
      throw e
    }
  }

  private suspend fun fetchItemByIdFromLocalDb(id: Int): StoryModel? {
    return dao.getById(id)
  }

  private suspend fun fetchItemByIdFromNetwork(id: Int): ResultWrapper<Response<StoryModel>> {
    return safeApiCall { api.getStoryById(id) }
  }

  private suspend fun storeInDb(item: StoryModel, type: StoryType) {
    item.storyType = type
    item.setDefaults()
//    Timber.d("Storing in localdb")
    dao.insert(item)
  }
}
