package com.mohammadfayaz.hn.ui.adapters.comments

import android.annotation.SuppressLint
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

//    init {
//      binding.authorNameTextView.setOnClickListener {
//        Toast.makeText(binding.root.context, "author-click", Toast.LENGTH_SHORT).show()
//      }
//    }

    @SuppressLint("SetTextI18n")
    fun bind(story: CommentModel) {

      var emptySpace = " "

      repeat(story.by.length * 2) {
        emptySpace += " "
      }

      binding.apply {
        authorNameTextView.text = story.by
        commentTextView.text =
          emptySpace +
          HtmlCompat.fromHtml(story.text, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
            .trimEnd()
        timeTextView.text = AppDateTimeUtils.whenDidThisHappen(story.time)
      }
    }
  }
}
