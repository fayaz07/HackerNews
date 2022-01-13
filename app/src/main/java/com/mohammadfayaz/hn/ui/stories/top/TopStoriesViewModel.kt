package com.mohammadfayaz.hn.ui.stories.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.hn.domain.usecases.stories.top.GetTopStoryIdsUseCase
import com.mohammadfayaz.hn.domain.usecases.stories.top.TopStoryPaginationUseCase
import com.mohammadfayaz.hn.ui.base.BaseFragment
import com.mohammadfayaz.hn.utils.AppConstants.API_ERROR
import com.mohammadfayaz.hn.utils.ViewEvent
import com.mohammadfayaz.hn.utils.error
import com.mohammadfayaz.hn.utils.load
import com.mohammadfayaz.hn.utils.success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopStoriesViewModel @Inject constructor(
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
        _liveData.error(response.message, API_ERROR, null)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = topStoryPaginationUseCase.invoke(idsList)
}
