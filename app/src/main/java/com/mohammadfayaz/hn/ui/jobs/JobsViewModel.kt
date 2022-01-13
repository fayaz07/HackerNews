package com.mohammadfayaz.hn.ui.jobs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.hn.domain.usecases.stories.top.GetTopStoryIdsUseCase
import com.mohammadfayaz.hn.domain.usecases.stories.top.TopStoryPaginationUseCase
import com.mohammadfayaz.hn.ui.base.BaseFragment
import com.mohammadfayaz.hn.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
  private val getTopStoryIdsUseCase: GetTopStoryIdsUseCase,
  private val topStoryPaginationUseCase: TopStoryPaginationUseCase
) : ViewModel() {

  private val _liveData = MutableLiveData<ViewEvent>()
  val liveData: LiveData<ViewEvent> = _liveData

  init {
    pullData()
  }

  fun pullData() {
    viewModelScope.launch {
      _liveData.load()
      val response = getTopStoryIdsUseCase.invoke()
      if (response.success)
        _liveData.success(response.result!!, response.message, BaseFragment.FETCHED_IDS)
      else
        _liveData.error(response.message, AppConstants.API_ERROR, null)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = topStoryPaginationUseCase.invoke(idsList)
}
