package com.mohammadfayaz.news.ui.ask_stories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mohammadfayaz.news.R
import com.mohammadfayaz.news.databinding.FragmentAskStoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AskStoriesFragment : Fragment() {

  private val viewModel: AskStoriesViewModel by viewModels()
  private lateinit var binding: FragmentAskStoriesBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentAskStoriesBinding.inflate(layoutInflater)
    return binding.root
  }
}
