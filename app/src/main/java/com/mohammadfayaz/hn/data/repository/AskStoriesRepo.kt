package com.mohammadfayaz.hn.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.paging.StoryPagingSource
import com.mohammadfayaz.hn.data.sources.local.source.StoriesLocalSource
import com.mohammadfayaz.hn.data.sources.network.source.StoriesNetworkSource
import com.mohammadfayaz.hn.domain.DataConfig.MAX_ITEMS_LIMIT
import com.mohammadfayaz.hn.domain.DataConfig.MAX_PAGE_SIZE
import com.mohammadfayaz.hn.domain.DataConfig.PRE_FETCH_DISTANCE
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.models.StoryType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AskStoriesRepo @Inject constructor(
  storiesNetworkSource: StoriesNetworkSource,
  storyLocalSource: StoriesLocalSource,
) : BaseStoryRepo(storiesNetworkSource, storyLocalSource) {

  private val storyType: StoryType = StoryType.ASK

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
