package com.mohammadfayaz.hn.domain.usecases.stories.ask

import com.mohammadfayaz.hn.data.repository.AskStoriesRepo
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetAskStoryUseCase @Inject constructor(private val askStoriesRepo: AskStoriesRepo) :
  BaseUseCase() {
  suspend fun invoke(id: Int): ApiResult<StoryModel> {
    return askStoriesRepo.fetchItemById(id)
  }
}
