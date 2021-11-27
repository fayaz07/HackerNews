package com.mohammadfayaz.news.ui.show_stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mohammadfayaz.news.BuildConfig
import com.mohammadfayaz.news.databinding.FragmentShowStoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowStoriesFragment : Fragment() {

  private val viewModel: ShowStoriesViewModel by viewModels()
  private lateinit var binding: FragmentShowStoriesBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentShowStoriesBinding.inflate(layoutInflater)

    binding.textView.text = viewModel.helloWorldText + "\n" + BuildConfig.hnBaseUrl


    viewModel.pullData()

    viewModel.liveData.observe(viewLifecycleOwner) {
      binding.textView.text = it
    }

    return binding.root
  }

}
