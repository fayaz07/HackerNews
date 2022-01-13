package com.mohammadfayaz.hn.domain.usecases.stories.job

import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.repository.JobStoriesRepo
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JobStoryPaginationUseCase @Inject constructor(private val jobStoriesRepo: JobStoriesRepo) :
  BaseUseCase() {
  fun invoke(idsList: List<Int>): Flow<PagingData<StoryModel>> {
    return jobStoriesRepo.getPaginatedFlow(idsList)
  }
}
