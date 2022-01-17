package com.mohammadfayaz.hn.ui.stories.ask.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mohammadfayaz.hn.R
import com.mohammadfayaz.hn.databinding.FragmentAskStoryDetailBinding
import com.mohammadfayaz.hn.domain.models.IdsModel
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.utils.AppDateTimeUtils
import com.mohammadfayaz.hn.utils.ViewEvent
import com.mohammadfayaz.hn.utils.extensions.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AskStoryDetailFragment : Fragment() {

  private lateinit var binding: FragmentAskStoryDetailBinding
  private val viewModel: AskStoryDetailViewModel by viewModels()

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

  private fun addObservers() {
    viewModel.viewEvent.observe(viewLifecycleOwner) {
      when (it) {
        is ViewEvent.Error<*> -> {
        }
        ViewEvent.Idle -> {
        }
        ViewEvent.Loading -> {
        }
        is ViewEvent.Success<*> -> {
          handleViewModelSuccessEvents(it)
        }
      }
    }
  }

  private fun handleViewModelSuccessEvents(it: ViewEvent.Success<*>) {
    when (it.code) {
      FAVOURITE_EVENT -> {
        binding.floatingActionButton.setImageDrawable(
          ContextCompat.getDrawable(
            requireContext(),
            if (it.data as Boolean) R.drawable.ic_baseline_favorite_24
            else R.drawable.ic_baseline_favorite_border_24
          )
        )
      }
    }
  }

  private fun registerViewEvents() {
    binding.apply {
      val storyItem: StoryModel? = arguments?.getParcelable("storyItem")

      if (storyItem != null) {
        viewModel.isFavourite(storyItem.id)
        updateUI(storyItem)
      } else {
        showError("Unable to show story details")
      }
    }
  }

  private fun updateUI(storyItem: StoryModel) {
    binding.apply {

      titleTextView.text = storyItem.title
      authorNameTextView.text = storyItem.by
      if (storyItem.text != null) {
        descriptionTextView.text = HtmlCompat.fromHtml(storyItem.text, FROM_HTML_MODE_COMPACT)
      } else {
        descriptionTextView.gone()
      }
      timeTextView.text = AppDateTimeUtils.formatDate(storyItem.time)
      scoreTextView.text = storyItem.score.toString()
      commentsTextView.text = storyItem.kids?.size.toString()

      viewCommentsButton.setOnClickListener {
        handleCommentsButtonClick(storyItem)
      }
    }
  }

  private fun showError(msg: String) {
    Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
      .show()
  }

  private fun handleCommentsButtonClick(storyItem: StoryModel) {
    if (storyItem.kids != null && storyItem.kids!!.isNotEmpty()) {
      val action =
        AskStoryDetailFragmentDirections.actionAskStoryDetailFragmentToCommentsFragment(
          IdsModel(storyItem.kids!!)
        )
      findNavController().navigate(action)
    } else {
      showError("No comments to show")
    }
  }

  companion object {
    const val FAVOURITE_EVENT = 1
  }
}
