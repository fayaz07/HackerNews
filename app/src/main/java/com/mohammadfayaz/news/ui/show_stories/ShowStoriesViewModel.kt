package com.mohammadfayaz.news.ui.show_stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.news.data.models.ShowStoryModel
import com.mohammadfayaz.news.domain.repository.ShowStoriesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShowStoriesViewModel @Inject constructor(private val repo: ShowStoriesRepo) : ViewModel() {
  val helloWorldText = "Hello from Show Stories View Model"

  private val _liveData = MutableLiveData<List<Int>>()
  val liveData: LiveData<List<Int>> = _liveData

  fun pullData() {
    viewModelScope.launch {
      val response = repo.pullTopStories()
      _liveData.postValue(response.body())
    }
  }

  fun getPaginated(idsList: List<Int>) = repo.getPaginatedFlow(idsList)

}
