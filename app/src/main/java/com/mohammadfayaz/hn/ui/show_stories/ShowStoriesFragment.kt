package com.mohammadfayaz.hn.ui.show_stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import com.mohammadfayaz.hn.data.models.StoryModel
import com.mohammadfayaz.hn.databinding.FragmentShowStoriesBinding
import com.mohammadfayaz.hn.network.models.response.IdsResponse
import com.mohammadfayaz.hn.ui.adapters.loading_adapter.LoadingIndicatorAdapter
import com.mohammadfayaz.hn.ui.adapters.show_stories.StoryItemClickListener
import com.mohammadfayaz.hn.ui.adapters.show_stories.StoryListAdapter
import com.mohammadfayaz.hn.ui.story_detail.StoryDetailedActivity
import com.mohammadfayaz.hn.utils.ViewEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ShowStoriesFragment : Fragment(), StoryItemClickListener {

  private val viewModel: ShowStoriesViewModel by viewModels()
  private lateinit var binding: FragmentShowStoriesBinding

  private lateinit var adapter: StoryListAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentShowStoriesBinding.inflate(layoutInflater)

    registerViewEvents()
    addObservers()

    return binding.root
  }

  private fun showLoader() {
    binding.progressBar.visibility = View.VISIBLE
  }

  private fun hideLoader() {
    binding.progressBar.visibility = View.GONE
  }

  private fun showErrorViews() {
    binding.errorViewLayout.root.visibility = View.VISIBLE
  }

  private fun hideErrorViews() {
    binding.errorViewLayout.root.visibility = View.GONE
  }

  private fun registerViewEvents() {
    adapter = StoryListAdapter(this)
    setupRecyclerView()

    binding.errorViewLayout.retryButton.setOnClickListener {
      viewModel.pullData()
    }
  }

  private fun setupRecyclerView() {
    binding.apply {
      recyclerView.setHasFixedSize(true)
      recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
        header = LoadingIndicatorAdapter { adapter.retry() },
        footer = LoadingIndicatorAdapter { adapter.retry() }
      )
    }
  }

  private fun addObservers() {
    viewModel.liveData.observe(viewLifecycleOwner) {
      handleViewEvents(it)
    }

    listenToLoadingStates()
  }

  private fun handleViewEvents(viewEvent: ViewEvent) {
    when (viewEvent) {
      is ViewEvent.Error<*> -> {
        binding.errorViewLayout.errorTextView.text = viewEvent.error
        hideLoader()
        showErrorViews()
      }
      ViewEvent.Idle -> {
        hideLoader()
      }
      ViewEvent.Loading -> {
        hideErrorViews()
        showLoader()
      }
      is ViewEvent.Success<*> -> {
        when (viewEvent.code) {
          FETCHED_IDS -> {
            listenToPaginationFlow(viewEvent.data!! as IdsResponse)
            hideLoader()
          }
        }
      }
    }
  }

  private fun listenToLoadingStates() {
    adapter.addLoadStateListener { loadStates ->
      binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
      if (loadStates.refresh is LoadState.Error) {
        showErrorViews()
        binding.errorViewLayout.errorTextView.text =
          (loadStates.refresh as LoadState.Error).error.localizedMessage
      } else {
        hideErrorViews()
      }
    }
  }

//  private fun showError(error: String) {
//    Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT).show()
//  }

  private fun listenToPaginationFlow(ids: IdsResponse) {
    lifecycleScope.launch {
      binding.progressBar.visibility = View.GONE
      viewModel.getPaginatedFlow(ids).collect {
        adapter.submitData(it)
      }
    }
  }

  override fun onClick(item: StoryModel) {
    Timber.d("$item ")
    StoryDetailedActivity.open(requireActivity(), item)
  }

  override fun onClickError() {
    Snackbar.make(binding.root, "Unable to open story", Snackbar.LENGTH_SHORT).show()
  }

  companion object {
    const val FETCHED_IDS: Int = 1
  }
}
