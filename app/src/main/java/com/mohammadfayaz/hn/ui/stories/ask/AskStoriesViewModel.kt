package com.mohammadfayaz.hn.ui.stories.ask

import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.hn.domain.usecases.stories.ask.AskStoryPaginationUseCase
import com.mohammadfayaz.hn.domain.usecases.stories.ask.GetAskStoryIdsUseCase
import com.mohammadfayaz.hn.ui.base.BaseFragment.Companion.FETCHED_IDS
import com.mohammadfayaz.hn.ui.base.BaseViewModel
import com.mohammadfayaz.hn.utils.AppConstants.API_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AskStoriesViewModel @Inject constructor(
  private val getAskStoryIdsUseCase: GetAskStoryIdsUseCase,
  private val askStoriesPaginationUseCase: AskStoryPaginationUseCase
) : BaseViewModel() {

  init {
    pullData()
  }

  fun pullData() {
    viewModelScope.launch {
      postLoading()
      val response = getAskStoryIdsUseCase.invoke()
      if (response.success)
        postSuccess(response.result!!, response.message, FETCHED_IDS)
      else
        postError(response.message, API_ERROR, null)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = askStoriesPaginationUseCase.invoke(idsList)
}
