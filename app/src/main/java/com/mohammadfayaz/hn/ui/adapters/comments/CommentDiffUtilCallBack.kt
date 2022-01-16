package com.mohammadfayaz.hn.ui.adapters.comments

import androidx.recyclerview.widget.DiffUtil
import com.mohammadfayaz.hn.domain.models.CommentModel

class CommentDiffUtilCallBack : DiffUtil.ItemCallback<CommentModel>() {
  override fun areItemsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
    return oldItem == newItem
  }
}
