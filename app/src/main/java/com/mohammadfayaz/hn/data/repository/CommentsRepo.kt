package com.mohammadfayaz.hn.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.paging.CommentsPagingSource
import com.mohammadfayaz.hn.domain.DataConfig
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.CommentModel
import com.mohammadfayaz.hn.domain.repository.ICommentsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommentsRepo @Inject constructor() : ICommentsRepo {
  override suspend fun fetchCommentById(id: Int): ApiResult<CommentModel> {
    return ApiResult.NetworkError
  }

  override suspend fun storeCommentInDb(id: Int) {
  }

  override suspend fun getCommentFromDb(id: Int) {
  }

  override fun getPaginatedFlow(ids: List<Int>): Flow<PagingData<CommentModel>> = Pager(
    config = PagingConfig(
      pageSize = DataConfig.MAX_ITEMS_LIMIT,
      maxSize = DataConfig.MAX_PAGE_SIZE,
      prefetchDistance = DataConfig.PRE_FETCH_DISTANCE,
      enablePlaceholders = false
    ),
    pagingSourceFactory = { CommentsPagingSource(::fetchCommentById, ids) }
  ).flow
}
