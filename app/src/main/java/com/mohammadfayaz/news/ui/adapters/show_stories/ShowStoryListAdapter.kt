package com.mohammadfayaz.news.ui.adapters.show_stories

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mohammadfayaz.news.data.models.ShowStoryModel

class ShowStoryListAdapter :
  ListAdapter<ShowStoryModel, ShowStoriesViewHolder>(ShowStoryDiffUtilCallBack()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowStoriesViewHolder {
    return ShowStoriesViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: ShowStoriesViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}
