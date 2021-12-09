package com.mohammadfayaz.hn.ui.show_stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.hn.domain.repository.ShowStoriesRepo
import com.mohammadfayaz.hn.ui.show_stories.ShowStoriesFragment.Companion.FETCHED_IDS
import com.mohammadfayaz.hn.utils.AppConstants.API_ERROR
import com.mohammadfayaz.hn.utils.ViewEvent
import com.mohammadfayaz.hn.utils.error
import com.mohammadfayaz.hn.utils.load
import com.mohammadfayaz.hn.utils.success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ShowStoriesViewModel @Inject constructor(private val repo: ShowStoriesRepo) : ViewModel() {

  private val _liveData = MutableLiveData<ViewEvent>()
  val liveData: LiveData<ViewEvent> = _liveData

  init {
    pullData()
  }

  fun pullData() {
    viewModelScope.launch {
      _liveData.load()
      val response = repo.fetchStories()
      if (response.success)
        _liveData.success(response.result!!, "", FETCHED_IDS)
      else
        _liveData.error(response.message + "\nUnable to fetch story ids", API_ERROR, null)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = repo.getPaginatedFlow(idsList)

}
