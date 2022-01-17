package com.mohammadfayaz.hn.domain.usecases.stories.ask

import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.repository.AskStoriesRepo
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AskStoryPaginationUseCase @Inject constructor(private val askStoriesRepo: AskStoriesRepo) :
  BaseUseCase() {
  fun invoke(idsList: List<Int>): Flow<PagingData<StoryModel>> {
    return askStoriesRepo.getPaginatedFlow(idsList)
  }
}
