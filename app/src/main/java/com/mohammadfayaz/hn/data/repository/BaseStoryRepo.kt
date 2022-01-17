package com.mohammadfayaz.hn.data.repository

import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.sources.local.source.StoriesLocalSource
import com.mohammadfayaz.hn.data.sources.network.source.StoriesNetworkSource
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.models.StoryType
import com.mohammadfayaz.hn.domain.repository.IBaseStoryRepo
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

abstract class BaseStoryRepo(
  private val storiesNetworkSource: StoriesNetworkSource,
  private val storyLocalSource: StoriesLocalSource
) : IBaseStoryRepo {

  abstract override suspend fun fetchItemById(id: Int): ApiResult<StoryModel>

  abstract override fun getPaginatedFlow(idsList: List<Int>): Flow<PagingData<StoryModel>>

  override suspend fun fetchItemByIdAndType(id: Int, type: StoryType): ApiResult<StoryModel> {
    val fromLocal = fetchItemByIdFromLocalDb(id)
    if (fromLocal != null) {
      Timber.d("Item exists in localdb $fromLocal")
      return ApiResult.OK(res = fromLocal)
    }

    val fromNetwork = fetchItemByIdFromNetwork(id)
    if (fromNetwork.success)
      storeItemInDb(fromNetwork.result!!, type)

    return fromNetwork
  }

  private suspend fun fetchItemByIdFromLocalDb(id: Int): StoryModel? {
    return storyLocalSource.getById(id)
  }

  private suspend fun fetchItemByIdFromNetwork(id: Int): ApiResult<StoryModel> {
    return storiesNetworkSource.getStoryById(id)
  }

  private suspend fun storeItemInDb(item: StoryModel, type: StoryType) {
    storyLocalSource.put(item, type)
  }
}
