package com.mohammadfayaz.hn.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mohammadfayaz.hn.databinding.FragmentUserInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

  private val viewModel: UserInfoViewModel by viewModels()
  private lateinit var binding: FragmentUserInfoBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentUserInfoBinding.inflate(layoutInflater)
    return binding.root
  }
}