package com.mohammadfayaz.hn.domain.usecases

import com.mohammadfayaz.hn.data.repository.IdsRepo
import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryType
import javax.inject.Inject

class GetShowStoryIdsUseCase @Inject constructor(private val idsRepo: IdsRepo) : BaseUseCase() {
  suspend fun invoke(): ApiResult<List<Int>> {
    return idsRepo.fetchStoryIds(StoryType.SHOW)
  }
}
