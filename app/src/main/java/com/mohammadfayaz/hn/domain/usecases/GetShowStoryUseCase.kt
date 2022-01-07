package com.mohammadfayaz.hn.domain.usecases

import com.mohammadfayaz.hn.data.repository.ShowStoriesRepo
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryModel

class GetShowStoryUseCase(private val showStoriesRepo: ShowStoriesRepo) : BaseUseCase() {
  suspend fun invoke(id: Int): ApiResult<StoryModel> {
    return showStoriesRepo.fetchItemById(id)
  }
}
