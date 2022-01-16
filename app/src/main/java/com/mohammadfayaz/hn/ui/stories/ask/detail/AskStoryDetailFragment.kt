package com.mohammadfayaz.hn.ui.stories.ask.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome
import com.mohammadfayaz.hn.databinding.FragmentAskStoryDetailBinding
import com.mohammadfayaz.hn.domain.models.StoryModel
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
        commentsImageView.icon =
          IconicsDrawable(binding.root.context, FontAwesome.Icon.faw_comment)
        scoreImageView.icon =
          IconicsDrawable(binding.root.context, FontAwesome.Icon.faw_heart)
        scoreTextView.text = it.score.toString()
        commentsTextView.text = it.kids?.size.toString()
      }
    }
  }

  private fun addObservers() {

  }

}
