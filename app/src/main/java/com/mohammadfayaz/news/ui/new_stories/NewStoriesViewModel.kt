package com.mohammadfayaz.news.ui.new_stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.news.repository.HackerNewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewStoriesViewModel @Inject constructor(private val repo: HackerNewsRepo) : ViewModel() {
  val helloWorldText = "Hello from New Stories View Model"

  private val _liveData = MutableLiveData<String>()
  val liveData: LiveData<String> = _liveData

  fun pullData() {
    viewModelScope.launch {
      val response = repo.pullTopStories()
      _liveData.postValue(response.body().toString())
    }
  }
}
