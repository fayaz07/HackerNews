package com.mohammadfayaz.hn.data.models

sealed class ApiResult<out T>(val result: T?, val success: Boolean, val message: String = "") {
  data class ERROR<out T>(val error: String) :
    ApiResult<T>(result = null, success = false, message = error)

  object NetworkError :
    ApiResult<Nothing>(
      result = null,
      success = false,
      message = "Unable to connect to the internet"
    )

  data class OK<out T>(val msg: String = "Fetched data", val res: T) :
    ApiResult<T>(success = true, message = msg, result = res)
}
