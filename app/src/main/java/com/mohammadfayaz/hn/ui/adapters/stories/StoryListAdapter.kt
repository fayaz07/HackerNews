package com.mohammadfayaz.hn.ui.adapters.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome
import com.mohammadfayaz.hn.databinding.LayoutStoryItemBinding
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.utils.icons.AppIcons

class StoryListAdapter constructor(private val listener: StoryItemClickListener) :
  PagingDataAdapter<StoryModel, StoryListAdapter.StoryViewHolder>(StoryDiffUtilCallBack()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
    return from(parent)
  }

  fun from(parent: ViewGroup): StoryViewHolder {
    return StoryViewHolder(
      LayoutStoryItemBinding.inflate(
        LayoutInflater.from(
          parent.context
        ),
        parent,
        false
      ),
    )
  }

  override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
    holder.bind(getItem(position)!!)
  }

  inner class StoryViewHolder constructor(
    private val binding: LayoutStoryItemBinding
  ) :
    RecyclerView.ViewHolder(binding.root) {

    init {
      binding.parentLayout.setOnClickListener {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
          val item = getItem(position)
          if (item != null) {
            listener.onClick(item)
            return@setOnClickListener
          }
        }

        // if didn't return call error
        listener.onClickError()
      }
    }

    fun bind(story: StoryModel) {
      binding.apply {
        authorNameTextView.text = story.by
        titleTextView.text = story.title
        commentsImageView.icon =
          AppIcons.fawComment(binding.root.context)
        scoreImageView.icon =
          AppIcons.fawHeart(binding.root.context)
        scoreTextView.text = story.score.toString()
        commentsTextView.text = story.kids?.size.toString()
        storyTypeTextView.text = story.storyType.string.uppercase()
      }
    }
  }
}
