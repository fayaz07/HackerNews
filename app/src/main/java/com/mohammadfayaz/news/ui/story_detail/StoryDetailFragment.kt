package com.mohammadfayaz.news.ui.story_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohammadfayaz.news.R

class StoryDetailFragment : Fragment() {

  private lateinit var viewModel: StoryDetailViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_story_detail, container, false)
  }

}