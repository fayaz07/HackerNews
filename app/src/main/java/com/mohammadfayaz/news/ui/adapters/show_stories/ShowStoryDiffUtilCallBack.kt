package com.mohammadfayaz.news.ui.adapters.show_stories

import androidx.recyclerview.widget.DiffUtil
import com.mohammadfayaz.news.data.models.ShowStoryModel

class ShowStoryDiffUtilCallBack : DiffUtil.ItemCallback<ShowStoryModel>() {
  override fun areItemsTheSame(oldItem: ShowStoryModel, newItem: ShowStoryModel): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: ShowStoryModel, newItem: ShowStoryModel): Boolean {
    return oldItem == newItem
  }
}
