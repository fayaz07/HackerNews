package com.mohammadfayaz.hn.ui.adapters.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohammadfayaz.hn.databinding.LayoutCommentBinding
import com.mohammadfayaz.hn.domain.models.CommentModel
import com.mohammadfayaz.hn.utils.AppDateTimeUtils

class CommentListAdapter :
  PagingDataAdapter<CommentModel, CommentListAdapter.CommentViewHolder>(CommentDiffUtilCallBack()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
    return from(parent)
  }

  fun from(parent: ViewGroup): CommentViewHolder {
    return CommentViewHolder(
      LayoutCommentBinding.inflate(
        LayoutInflater.from(
          parent.context
        ),
        parent,
        false
      ),
    )
  }

  override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
    holder.bind(getItem(position)!!)
  }

  inner class CommentViewHolder constructor(
    private val binding: LayoutCommentBinding
  ) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(story: CommentModel) {
      binding.apply {
        authorNameTextView.text = story.by
        commentTextView.text = HtmlCompat.fromHtml(story.text, HtmlCompat.FROM_HTML_MODE_COMPACT)
        timeTextView.text = AppDateTimeUtils.whenDidThisHappen(story.time)
      }
    }
  }
}
