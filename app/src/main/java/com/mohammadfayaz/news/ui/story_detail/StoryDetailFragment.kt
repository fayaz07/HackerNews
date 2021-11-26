package com.mohammadfayaz.news.ui.story_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mohammadfayaz.news.R
import com.mohammadfayaz.news.databinding.FragmentStoryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoryDetailFragment : Fragment() {

  private val viewModel: StoryDetailViewModel by viewModels()
  private lateinit var binding: FragmentStoryDetailBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentStoryDetailBinding.inflate(layoutInflater)
    return binding.root
  }

}