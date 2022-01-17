package com.mohammadfayaz.hn.ui.stories.ask.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.mohammadfayaz.hn.databinding.FragmentAskStoryDetailBinding
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.ui.adapters.comments.CommentListAdapter
import com.mohammadfayaz.hn.ui.adapters.loading.LoadingIndicatorAdapter
import com.mohammadfayaz.hn.ui.adapters.stories.StoryListAdapter
import com.mohammadfayaz.hn.utils.AppDateTimeUtils
import com.mohammadfayaz.hn.utils.extensions.gone
import com.mohammadfayaz.hn.utils.extensions.showHide
import com.mohammadfayaz.hn.utils.icons.AppIcons
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class AskStoryDetailFragment : Fragment() {

  private lateinit var binding: FragmentAskStoryDetailBinding
  private val viewModel: AskStoryDetailViewModel by viewModels()

  private lateinit var adapter: CommentListAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentAskStoryDetailBinding.inflate(layoutInflater)

    registerViewEvents()
    addObservers()

    return binding.root
  }

  private fun registerViewEvents() {
    binding.apply {

      adapter = CommentListAdapter()
      commentsRecyclerView.setHasFixedSize(true)
      commentsRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
        header = LoadingIndicatorAdapter { adapter.retry() },
        footer = LoadingIndicatorAdapter { adapter.retry() }
      )

      val storyItem: StoryModel? = arguments?.getParcelable("storyItem")
      storyItem?.let {
        titleTextView.text = it.title
        authorNameTextView.text = it.by
        if (it.text != null) {
          descriptionTextView.text = HtmlCompat.fromHtml(it.text, FROM_HTML_MODE_COMPACT)
        } else {
          descriptionTextView.gone()
        }
        timeTextView.text = AppDateTimeUtils.formatDate(it.time)
        commentsImageView.icon = AppIcons.fawComment(binding.root.context)
        scoreImageView.icon = AppIcons.fawHeart(binding.root.context)
        scoreTextView.text = it.score.toString()
        commentsTextView.text = it.kids?.size.toString()

        listenToPaginationFlow(storyItem.kids!!)
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
      viewModel.getComments(ids).collect {
        Timber.d("Adding comments: $it")
        adapter.submitData(it)
      }
    }
  }
}
