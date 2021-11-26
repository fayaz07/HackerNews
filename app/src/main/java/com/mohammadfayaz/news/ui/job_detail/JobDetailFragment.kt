package com.mohammadfayaz.news.ui.job_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mohammadfayaz.news.databinding.FragmentJobDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobDetailFragment : Fragment() {

  private val viewModel: JobDetailViewModel by viewModels()
  private lateinit var binding: FragmentJobDetailBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentJobDetailBinding.inflate(layoutInflater)
    return binding.root
  }

}