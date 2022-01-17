package com.mohammadfayaz.hn.ui.stories.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.mohammadfayaz.hn.databinding.FragmentCommentsBinding
import com.mohammadfayaz.hn.domain.models.IdsModel
import com.mohammadfayaz.hn.ui.adapters.comments.CommentListAdapter
import com.mohammadfayaz.hn.ui.adapters.loading.LoadingIndicatorAdapter
import com.mohammadfayaz.hn.utils.extensions.showHide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommentsFragment : Fragment() {

  private lateinit var binding: FragmentCommentsBinding
  private val viewModel: CommentsViewModel by viewModels()

  private lateinit var adapter: CommentListAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentCommentsBinding.inflate(layoutInflater)

    registerViewEvents()
    addObservers()

    return binding.root
  }

  private fun registerViewEvents() {
    binding.apply {
      adapter = CommentListAdapter()
      recyclerView.setHasFixedSize(true)
      recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
        header = LoadingIndicatorAdapter { adapter.retry() },
        footer = LoadingIndicatorAdapter { adapter.retry() }
      )

      val idArguments: IdsModel? = arguments?.getParcelable("ids")
      if (idArguments != null) {
        listenToPaginationFlow(idArguments.ids)
      }
    }
  }

  private fun addObservers() {
    listenToLoadingStates()
  }

  private fun listenToLoadingStates() {
    adapter.addLoadStateListener { loadStates ->
      binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
      if (loadStates.refresh is LoadState.Error) {
        binding.errorViewLayout.root.showHide(true)
        binding.errorViewLayout.errorTextView.text =
          (loadStates.refresh as LoadState.Error).error.localizedMessage
      } else {
        binding.errorViewLayout.root.showHide(false)
      }
    }
  }

  private fun listenToPaginationFlow(ids: List<Int>) {
    lifecycleScope.launch {
      binding.progressBar.visibility = View.GONE
      viewModel.getComments(ids).collect { adapter.submitData(it) }
    }
  }
}
