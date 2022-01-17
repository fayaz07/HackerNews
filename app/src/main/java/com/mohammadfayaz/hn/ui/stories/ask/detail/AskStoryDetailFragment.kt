package com.mohammadfayaz.hn.ui.stories.ask.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mohammadfayaz.hn.databinding.FragmentAskStoryDetailBinding
import com.mohammadfayaz.hn.domain.models.IdsModel
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.utils.AppDateTimeUtils
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

    return binding.root
  }

  private fun registerViewEvents() {
    viewModel.init()

    binding.apply {
      val storyItem: StoryModel? = arguments?.getParcelable("storyItem")

      if (storyItem != null) {
        storyItem.let {
          titleTextView.text = it.title
          authorNameTextView.text = it.by
          if (it.text != null) {
            descriptionTextView.text = HtmlCompat.fromHtml(it.text, FROM_HTML_MODE_COMPACT)
          } else {
            descriptionTextView.gone()
          }
          timeTextView.text = AppDateTimeUtils.formatDate(it.time)
          scoreTextView.text = it.score.toString()
          commentsTextView.text = it.kids?.size.toString()

          viewCommentsButton.setOnClickListener {
            handleCommentsButtonClick(storyItem)
          }
        }
      } else {
        showError("Unable to show story details")
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
}
