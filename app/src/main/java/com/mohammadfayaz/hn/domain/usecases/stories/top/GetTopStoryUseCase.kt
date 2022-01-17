package com.mohammadfayaz.hn.domain.usecases.stories.top

import com.mohammadfayaz.hn.data.repository.TopStoriesRepo
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetTopStoryUseCase @Inject constructor(private val topStoriesRepo: TopStoriesRepo) :
  BaseUseCase() {
  suspend fun invoke(id: Int): ApiResult<StoryModel> {
    return topStoriesRepo.fetchItemById(id)
  }
}
