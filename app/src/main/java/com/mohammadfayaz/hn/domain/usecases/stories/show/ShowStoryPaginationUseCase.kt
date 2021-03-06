package com.mohammadfayaz.hn.domain.usecases.stories.show

import androidx.paging.PagingData
import com.mohammadfayaz.hn.data.repository.ShowStoriesRepo
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowStoryPaginationUseCase @Inject constructor(private val repo: ShowStoriesRepo) :
  BaseUseCase() {
  fun invoke(idsList: List<Int>): Flow<PagingData<StoryModel>> {
    return repo.getPaginatedFlow(idsList)
  }
}
