package com.mohammadfayaz.hn.ui.story_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mohammadfayaz.hn.databinding.FragmentStoryDetailBinding
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
