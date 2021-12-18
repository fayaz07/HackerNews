package com.mohammadfayaz.hn.ui.top_stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mohammadfayaz.hn.databinding.FragmentTopStoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopStoriesFragment : Fragment() {

  private val viewModel: TopStoriesViewModel by viewModels()
  private lateinit var binding: FragmentTopStoriesBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentTopStoriesBinding.inflate(layoutInflater)
    return binding.root
  }
}
