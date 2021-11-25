package com.mohammadfayaz.news.ui.job_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohammadfayaz.news.R

class JobDetailFragment : Fragment() {

  private lateinit var viewModel: JobDetailViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_job_detail, container, false)
  }

}