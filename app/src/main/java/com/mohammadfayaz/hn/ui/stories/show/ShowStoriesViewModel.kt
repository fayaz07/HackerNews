package com.mohammadfayaz.hn.ui.stories.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.hn.domain.usecases.stories.show.GetShowStoryIdsUseCase
import com.mohammadfayaz.hn.domain.usecases.stories.show.ShowStoryPaginationUseCase
import com.mohammadfayaz.hn.ui.base.BaseFragment.Companion.FETCHED_IDS
import com.mohammadfayaz.hn.utils.AppConstants.API_ERROR
import com.mohammadfayaz.hn.utils.ViewEvent
import com.mohammadfayaz.hn.utils.error
import com.mohammadfayaz.hn.utils.load
import com.mohammadfayaz.hn.utils.success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowStoriesViewModel @Inject constructor(
  private val getShowStoryIdsUseCase: GetShowStoryIdsUseCase,
  private val showStoriesPaginationUseCase: ShowStoryPaginationUseCase
) : ViewModel() {

  private val _liveData = MutableLiveData<ViewEvent>()
  val liveData: LiveData<ViewEvent> = _liveData

  init {
    pullData()
  }

  fun pullData() {
    viewModelScope.launch {
      _liveData.load()
      val response = getShowStoryIdsUseCase.invoke()
      if (response.success)
        _liveData.success(response.result!!, response.message, FETCHED_IDS)
      else
        _liveData.error(response.message, API_ERROR, null)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = showStoriesPaginationUseCase.invoke(idsList)
}
