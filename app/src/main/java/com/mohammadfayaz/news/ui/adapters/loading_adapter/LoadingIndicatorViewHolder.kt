package com.mohammadfayaz.news.ui.adapters.loading_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.mohammadfayaz.news.databinding.LayoutLoadingIndicatorBinding

class LoadingIndicatorViewHolder private constructor(
  private val binding: LayoutLoadingIndicatorBinding,
  retry: () -> Unit
) :
  RecyclerView.ViewHolder(binding.root) {

  init {
    binding.retryButton.setOnClickListener { retry() }
  }

  fun bind(loadState: LoadState) {
    binding.apply {
      progressbar.isVisible = loadState is LoadState.Loading
      statusTextView.isVisible = loadState !is LoadState.Loading
      retryButton.isVisible = loadState !is LoadState.Loading
    }
  }

  companion object {
    fun from(parent: ViewGroup, retry: () -> Unit): LoadingIndicatorViewHolder {
      return LoadingIndicatorViewHolder(
        LayoutLoadingIndicatorBinding.inflate(
          LayoutInflater.from(
            parent.context
          ),
          parent,
          false
        ),
        retry
      )
    }
  }
}
