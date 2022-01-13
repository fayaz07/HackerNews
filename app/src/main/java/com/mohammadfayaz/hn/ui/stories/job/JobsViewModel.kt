package com.mohammadfayaz.hn.ui.stories.job

import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.hn.domain.usecases.stories.job.GetJobStoryIdsUseCase
import com.mohammadfayaz.hn.domain.usecases.stories.job.JobStoryPaginationUseCase
import com.mohammadfayaz.hn.ui.base.BaseFragment
import com.mohammadfayaz.hn.ui.base.BaseViewModel
import com.mohammadfayaz.hn.utils.AppConstants.API_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
  private val getJobStoryIdsUseCase: GetJobStoryIdsUseCase,
  private val jobStoryPaginationUseCase: JobStoryPaginationUseCase
) : BaseViewModel() {

  init {
    pullData()
  }

  fun pullData() {
    viewModelScope.launch {
      postLoading()
      val response = getJobStoryIdsUseCase.invoke()
      if (response.success)
        postSuccess(response.result!!, response.message, BaseFragment.FETCHED_IDS)
      else
        postError(response.message, API_ERROR, null)
    }
  }

  fun getPaginatedFlow(idsList: List<Int>) = jobStoryPaginationUseCase.invoke(idsList)
}
