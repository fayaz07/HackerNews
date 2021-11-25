package com.mohammadfayaz.news.ui.user_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohammadfayaz.news.R

class UserInfoFragment : Fragment() {

  private lateinit var viewModel: UserInfoViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_user_info, container, false)
  }

}