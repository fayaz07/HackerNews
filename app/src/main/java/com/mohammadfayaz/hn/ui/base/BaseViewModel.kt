package com.mohammadfayaz.hn.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohammadfayaz.hn.utils.AppConstants.INTERNET_ERROR
import com.mohammadfayaz.hn.utils.ViewEvent
import com.mohammadfayaz.hn.utils.error
import com.mohammadfayaz.hn.utils.idle
import com.mohammadfayaz.hn.utils.load
import com.mohammadfayaz.hn.utils.success

open class BaseViewModel : ViewModel() {
  private val _viewEvent = MutableLiveData<ViewEvent>()
  val viewEvent: LiveData<ViewEvent> = _viewEvent

  fun postNetworkError() {
    _viewEvent.error(INTERNET_ERROR, BaseFragment.NETWORK_ERROR, null)
  }

  fun postLoading() {
    _viewEvent.load()
  }

  fun postIdle() {
    _viewEvent.idle()
  }

  fun postSuccess(data: Any?, message: String, code: Int) {
    _viewEvent.success(data, message, code)
  }

  fun postError(error: String, code: Int, data: Any?) {
    _viewEvent.error(error, code, data)
  }
}
