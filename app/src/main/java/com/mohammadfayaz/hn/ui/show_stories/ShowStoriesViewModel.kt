package com.mohammadfayaz.hn.ui.show_stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.hn.domain.repository.ShowStoriesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ShowStoriesViewModel @Inject constructor(private val repo: ShowStoriesRepo) : ViewModel() {

  init {
    pullData()
  }

  private val _liveData = MutableLiveData<List<Int>>()
  val liveData: LiveData<List<Int>> = _liveData

  private fun pullData() {
    viewModelScope.launch {
      val response = repo.fetchStories()
      if (response.success)
        _liveData.postValue(response.result!!)
      else{
        Timber.e(response.message)
      }
//      else
//        _liveData.postValue(response.message)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = repo.getPaginatedFlow(idsList)

}
