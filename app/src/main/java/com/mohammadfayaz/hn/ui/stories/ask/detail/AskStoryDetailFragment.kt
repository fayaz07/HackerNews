package com.mohammadfayaz.hn.ui.stories.ask.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.fragment.app.viewModels
import com.mohammadfayaz.hn.databinding.FragmentAskStoryDetailBinding
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.utils.AppDateTimeUtils
import com.mohammadfayaz.hn.utils.extensions.gone
import com.mohammadfayaz.hn.utils.icons.AppIcons
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AskStoryDetailFragment : Fragment() {

  private lateinit var binding: FragmentAskStoryDetailBinding
  private val viewModel: AskStoryDetailViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentAskStoryDetailBinding.inflate(layoutInflater)

    registerViewEvents()
    addObservers()

    return binding.root
  }

  private fun registerViewEvents() {
    binding.apply {
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
      }
    }
  }

  private fun addObservers() {

  }

}
