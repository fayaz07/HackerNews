package com.mohammadfayaz.news.ui.show_stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mikepenz.iconics.Iconics
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome
import com.mikepenz.iconics.utils.icon
import com.mohammadfayaz.news.BuildConfig
import com.mohammadfayaz.news.databinding.FragmentShowStoriesBinding
import com.mohammadfayaz.news.ui.adapters.show_stories.ShowStoryListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowStoriesFragment : Fragment() {

  private val viewModel: ShowStoriesViewModel by viewModels()
  private lateinit var binding: FragmentShowStoriesBinding

  private lateinit var adapter: ShowStoryListAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentShowStoriesBinding.inflate(layoutInflater)

//    binding.textView.text = viewModel.helloWorldText + "\n" + BuildConfig.hnBaseUrl

    adapter = ShowStoryListAdapter()
    binding.recyclerView.adapter = adapter

    viewModel.pullData()

    viewModel.liveData.observe(viewLifecycleOwner) {
//      binding.textView.text = it
    }

    viewModel.liveData2.observe(viewLifecycleOwner) {
//      Toast.makeText(requireContext(), it.size.toString(), Toast.LENGTH_LONG).show()
//      binding.textView.text = it.size.toString()
      adapter.submitList(it)
    }

//    binding.imageaaa.icon = IconicsDrawable(requireContext(), FontAwesome.Icon.faw_comment)
    return binding.root
  }

}
