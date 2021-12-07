package com.mohammadfayaz.hn.ui.adapters.loading_adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class LoadingIndicatorAdapter constructor(private val retry: ()->Unit): LoadStateAdapter<LoadingIndicatorViewHolder>() {
  override fun onBindViewHolder(holder: LoadingIndicatorViewHolder, loadState: LoadState) {
    holder.bind(loadState)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    loadState: LoadState
  ): LoadingIndicatorViewHolder {
    return LoadingIndicatorViewHolder.from(parent, retry)
  }
}
