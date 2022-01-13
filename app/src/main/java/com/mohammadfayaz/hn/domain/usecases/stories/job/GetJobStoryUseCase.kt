package com.mohammadfayaz.hn.domain.usecases.stories.job

import com.mohammadfayaz.hn.data.repository.JobStoriesRepo
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetJobStoryUseCase @Inject constructor(private val jobStoriesRepo: JobStoriesRepo) :
  BaseUseCase() {
  suspend fun invoke(id: Int): ApiResult<StoryModel> {
    return jobStoriesRepo.fetchItemById(id)
  }
}
