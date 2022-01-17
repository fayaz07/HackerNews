package com.mohammadfayaz.hn.domain.usecases.comments

import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.repository.CommentsRepo
import com.mohammadfayaz.hn.domain.models.CommentModel
import com.mohammadfayaz.hn.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommentsPaginationUseCase @Inject constructor(private val repo: CommentsRepo) :
  BaseUseCase() {
  fun invoke(ids: List<Int>): Flow<PagingData<CommentModel>> {
    return repo.getPaginatedFlow(ids)
  }
}

