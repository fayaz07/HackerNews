package com.mohammadfayaz.hn.ui.adapters.stories

import com.mohammadfayaz.hn.domain.models.StoryModel

interface StoryItemClickListener {
  fun onClick(item: StoryModel)

  fun onClickError()
}
