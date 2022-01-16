package com.mohammadfayaz.hn.ui.stories.ask.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mohammadfayaz.hn.databinding.FragmentAskStoryDetailBinding
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

    return binding.root
  }

}
