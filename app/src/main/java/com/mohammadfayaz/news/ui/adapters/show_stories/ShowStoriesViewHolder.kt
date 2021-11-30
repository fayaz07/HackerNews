package com.mohammadfayaz.news.ui.adapters.show_stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome
import com.mohammadfayaz.news.data.models.ShowStoryModel
import com.mohammadfayaz.news.databinding.LayoutShowStoryBinding

class ShowStoriesViewHolder private constructor(private val binding: LayoutShowStoryBinding) :
  RecyclerView.ViewHolder(binding.root) {

  fun bind(story: ShowStoryModel) {
    binding.authorNameTextView.text = story.by
    binding.titleTextView.text = story.title
    binding.commentsImageView.icon =
      IconicsDrawable(binding.root.context, FontAwesome.Icon.faw_comment)
    binding.scoreImageView.icon =
      IconicsDrawable(binding.root.context, FontAwesome.Icon.faw_heart)
    binding.scoreTextView.text = story.score.toString()
    binding.commentsTextView.text = story.kids.size.toString()
  }

  companion object {
    fun from(parent: ViewGroup): ShowStoriesViewHolder {
      return ShowStoriesViewHolder(
        LayoutShowStoryBinding.inflate(
          LayoutInflater.from(
            parent.context
          ),
          parent,
          false
        )
      )
    }
  }
}