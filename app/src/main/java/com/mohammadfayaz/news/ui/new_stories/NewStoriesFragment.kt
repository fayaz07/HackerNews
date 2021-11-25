package com.mohammadfayaz.news.ui.new_stories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohammadfayaz.news.R

class NewStoriesFragment : Fragment() {
  private lateinit var viewModel: NewStoriesViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_new_stories, container, false)
  }

}