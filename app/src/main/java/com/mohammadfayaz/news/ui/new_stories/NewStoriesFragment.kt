package com.mohammadfayaz.news.ui.new_stories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mohammadfayaz.news.BuildConfig
import com.mohammadfayaz.news.R
import com.mohammadfayaz.news.databinding.FragmentNewStoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewStoriesFragment : Fragment() {

  private val viewModel: NewStoriesViewModel by viewModels()
  private lateinit var binding: FragmentNewStoriesBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentNewStoriesBinding.inflate(layoutInflater)

    binding.textView.text = viewModel.helloWorldText + "\n" + BuildConfig.hnBaseUrl

    return binding.root
  }

}
