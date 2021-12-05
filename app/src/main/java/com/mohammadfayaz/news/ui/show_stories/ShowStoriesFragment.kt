package com.mohammadfayaz.news.ui.show_stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mikepenz.iconics.Iconics
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome
import com.mikepenz.iconics.utils.icon
import com.mohammadfayaz.news.BuildConfig
import com.mohammadfayaz.news.databinding.FragmentShowStoriesBinding
import com.mohammadfayaz.news.ui.adapters.loading_adapter.LoadingIndicatorAdapter
import com.mohammadfayaz.news.ui.adapters.loading_adapter.LoadingIndicatorViewHolder
import com.mohammadfayaz.news.ui.adapters.show_stories.ShowStoryListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

    registerViewEvents()
    addObservers()

    return binding.root
  }

  private fun registerViewEvents() {
    adapter = ShowStoryListAdapter()
    binding.apply {
      recyclerView.setHasFixedSize(true)
      recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
        header = LoadingIndicatorAdapter { adapter.retry() },
        footer = LoadingIndicatorAdapter { adapter.retry() }
      )
    }
  }

  private fun addObservers() {
    viewModel.liveData.observe(viewLifecycleOwner) {

      lifecycleScope.launch {
        viewModel.getPaginated(it).collect {
          adapter.submitData(it)
        }
      }
    }
  }

}
