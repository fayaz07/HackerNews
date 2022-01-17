package com.mohammadfayaz.hn.ui.stories.top

import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.hn.domain.usecases.stories.top.GetTopStoryIdsUseCase
import com.mohammadfayaz.hn.domain.usecases.stories.top.TopStoryPaginationUseCase
import com.mohammadfayaz.hn.ui.base.BaseFragment
import com.mohammadfayaz.hn.ui.base.BaseViewModel
import com.mohammadfayaz.hn.utils.AppConstants.API_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopStoriesViewModel @Inject constructor(
  private val getTopStoryIdsUseCase: GetTopStoryIdsUseCase,
  private val topStoryPaginationUseCase: TopStoryPaginationUseCase
) : BaseViewModel() {

  init {
    pullData()
  }

  fun pullData() {
    viewModelScope.launch {
      postLoading()
      val response = getTopStoryIdsUseCase.invoke()
      if (response.success)
        postSuccess(response.result!!, response.message, BaseFragment.FETCHED_IDS)
      else
        postError(response.message, API_ERROR, null)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = topStoryPaginationUseCase.invoke(idsList)
}
