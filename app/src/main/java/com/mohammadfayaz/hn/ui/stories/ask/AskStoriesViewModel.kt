package com.mohammadfayaz.hn.ui.stories.ask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.hn.domain.usecases.stories.ask.AskStoryPaginationUseCase
import com.mohammadfayaz.hn.domain.usecases.stories.ask.GetAskStoryIdsUseCase
import com.mohammadfayaz.hn.ui.stories.show.ShowStoriesFragment
import com.mohammadfayaz.hn.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AskStoriesViewModel @Inject constructor(
  private val getAskStoryIdsUseCase: GetAskStoryIdsUseCase,
  private val askStoriesPaginationUseCase: AskStoryPaginationUseCase
) : ViewModel() {

  private val _liveData = MutableLiveData<ViewEvent>()
  val liveData: LiveData<ViewEvent> = _liveData

  init {
    pullData()
  }

  fun pullData() {
    viewModelScope.launch {
      _liveData.load()
      val response = getAskStoryIdsUseCase.invoke()
      if (response.success)
        _liveData.success(response.result!!, response.message, ShowStoriesFragment.FETCHED_IDS)
      else
        _liveData.error(response.message, AppConstants.API_ERROR, null)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = askStoriesPaginationUseCase.invoke(idsList)
}
