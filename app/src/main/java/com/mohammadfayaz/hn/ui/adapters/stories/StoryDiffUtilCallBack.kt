package com.mohammadfayaz.hn.ui.adapters.stories

import androidx.recyclerview.widget.DiffUtil
import com.mohammadfayaz.hn.domain.models.StoryModel

class StoryDiffUtilCallBack : DiffUtil.ItemCallback<StoryModel>() {
  override fun areItemsTheSame(oldItem: StoryModel, newItem: StoryModel): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: StoryModel, newItem: StoryModel): Boolean {
    return oldItem == newItem
  }
}
