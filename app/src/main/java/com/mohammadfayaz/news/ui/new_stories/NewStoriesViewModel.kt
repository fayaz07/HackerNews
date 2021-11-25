package com.mohammadfayaz.news.ui.new_stories

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewStoriesViewModel @Inject constructor() : ViewModel() {
  val helloWorldText = "Hello from New Stories View Model"
}
