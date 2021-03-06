package com.mohammadfayaz.hn.domain.repository

import androidx.paging.PagingData
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.CommentModel
import kotlinx.coroutines.flow.Flow

interface ICommentsRepo {
  suspend fun fetchCommentById(id: Int): ApiResult<CommentModel>

  fun getPaginatedFlow(ids: List<Int>): Flow<PagingData<CommentModel>>
}
