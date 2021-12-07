package com.mohammadfayaz.hn.ui.adapters.show_stories

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.mohammadfayaz.hn.data.models.StoryModel

class StoryListAdapter :
  PagingDataAdapter<StoryModel, StoryViewHolder>(StoryDiffUtilCallBack()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
    return StoryViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
    holder.bind(getItem(position)!!)
  }
}
