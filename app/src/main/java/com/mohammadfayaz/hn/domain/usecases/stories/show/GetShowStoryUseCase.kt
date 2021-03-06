package com.mohammadfayaz.hn.domain.usecases.stories.show

import com.mohammadfayaz.hn.data.repository.ShowStoriesRepo
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetShowStoryUseCase @Inject constructor(private val showStoriesRepo: ShowStoriesRepo) :
  BaseUseCase() {
  suspend fun invoke(id: Int): ApiResult<StoryModel> {
    return showStoriesRepo.fetchItemById(id)
  }
}
