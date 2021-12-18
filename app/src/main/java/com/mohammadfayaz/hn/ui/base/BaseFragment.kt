package com.mohammadfayaz.hn.ui.base

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.mohammadfayaz.hn.data.models.StoryModel
import com.mohammadfayaz.hn.ui.adapters.show_stories.StoryItemClickListener
import com.mohammadfayaz.hn.ui.story_detail.StoryDetailedActivity
import timber.log.Timber

abstract class BaseFragment : Fragment(), StoryItemClickListener {

  private lateinit var binding: ViewBinding

  abstract fun registerViewEvents()
  abstract fun addObservers()

  fun setBinding(b: ViewBinding) {
    binding = b
  }

  private fun showError(msg: String) {
    Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
  }

  override fun onClick(item: StoryModel) {
    Timber.d("$item ")
    if (item.url == null) {
      showError("Url or webpage for the story was removed")
      return
    }
    StoryDetailedActivity.open(requireActivity(), item)
  }

  override fun onClickError() {
    showError("Unable to open story")
  }
}
