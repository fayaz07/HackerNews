package com.mohammadfayaz.news.ui.jobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mohammadfayaz.news.R
import com.mohammadfayaz.news.databinding.FragmentJobsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobsFragment : Fragment() {

  private val viewModel: JobsViewModel by viewModels()
  private lateinit var binding: FragmentJobsBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentJobsBinding.inflate(layoutInflater)
    return binding.root
  }


}