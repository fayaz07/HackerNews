package com.mohammadfayaz.news.ui.jobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohammadfayaz.news.R

class JobsFragment : Fragment() {

  private lateinit var viewModel: JobsViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_jobs, container, false)
  }


}