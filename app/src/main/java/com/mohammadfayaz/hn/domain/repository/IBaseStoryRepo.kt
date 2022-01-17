package com.mohammadfayaz.hn.domain.repository

import androidx.paging.PagingData
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.models.StoryType
import kotlinx.coroutines.flow.Flow

interface IBaseStoryRepo {
  suspend fun fetchItemById(id: Int): ApiResult<StoryModel>

  fun getPaginatedFlow(idsList: List<Int>): Flow<PagingData<StoryModel>>

  suspend fun fetchItemByIdAndType(id: Int, type: StoryType): ApiResult<StoryModel>
}
