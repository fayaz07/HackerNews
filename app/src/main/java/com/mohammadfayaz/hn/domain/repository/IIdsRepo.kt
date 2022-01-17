package com.mohammadfayaz.hn.domain.repository

import com.mohammadfayaz.hn.domain.models.ApiResult
import com.mohammadfayaz.hn.domain.models.StoryType

interface IIdsRepo {
  suspend fun fetchStoryIds(storyType: StoryType): ApiResult<List<Int>>
}
