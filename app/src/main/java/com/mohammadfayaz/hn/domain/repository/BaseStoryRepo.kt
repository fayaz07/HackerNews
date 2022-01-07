package com.mohammadfayaz.hn.domain.repository

import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.sources.local.dao.IdsDao
import com.mohammadfayaz.hn.data.sources.local.dao.StoryDao
import com.mohammadfayaz.hn.data.sources.local.source.IdsLocalSource
import com.mohammadfayaz.hn.data.sources.local.source.StoriesLocalSource
import com.mohammadfayaz.hn.data.sources.network.ResultWrapper
import com.mohammadfayaz.hn.data.sources.network.ResultWrapper.Companion.safeApiCall
import com.mohammadfayaz.hn.data.sources.network.api.HackerNewsAPI
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryIdModel
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.models.StoryType
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

abstract class BaseStoryRepo(
  private val api: HackerNewsAPI,
  private val storyLocalSource: StoriesLocalSource,
  private val idsLocalSource: IdsLocalSource
) {

  abstract suspend fun fetchStoryIds(): ApiResult<List<Int>>

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
        ResultWrapper.NetworkError -> ApiResult.NetworkError
        is ResultWrapper.Success -> {
          if (fromNetwork.value.body() != null) {
            storeItemInDb(fromNetwork.value.body()!!, type)
            ApiResult.OK(res = fromNetwork.value.body()!!)
          } else {
            ApiResult.ERROR("Unable to fetch data")
          }
        }
      }
    } catch (e: Exception) {
      e.printStackTrace()
      throw e
    }
  }

  private suspend fun fetchItemByIdFromLocalDb(id: Int): StoryModel? {
    return storyLocalSource.getById(id)
  }

  private suspend fun fetchItemByIdFromNetwork(id: Int): ResultWrapper<Response<StoryModel>> {
    return safeApiCall { api.getStoryById(id) }
  }

  private suspend fun storeItemInDb(item: StoryModel, type: StoryType) {
    storyLocalSource.put(item, type)
  }

  suspend fun fetchIdsFromDb(type: StoryType): List<StoryIdModel> {
    return idsLocalSource.getIdsByStoryType(type)
  }

  suspend fun storeIdsInDb(list: List<StoryIdModel>) {
    idsLocalSource.putIds(list)
  }
}
