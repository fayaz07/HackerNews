package com.mohammadfayaz.hn.network

import okio.IOException
import retrofit2.HttpException
import timber.log.Timber

sealed class ResultWrapper<out T> {
  data class Success<out T>(val value: T) : ResultWrapper<T>()
  data class GenericError(val code: Int, val error: String) :
    ResultWrapper<Nothing>()

  object NetworkError : ResultWrapper<Nothing>()

  companion object {

    const val SOMETHING_WENT_WRONG: String = "Something went wrong"
    const val SOMETHING_WENT_WRONG_CODE: Int = 500

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
      var response: ResultWrapper<T> = GenericError(
        SOMETHING_WENT_WRONG_CODE,
        SOMETHING_WENT_WRONG
      )
      try {
        val res = apiCall.invoke()
//    Timber.tag("ttt").d(res.toString())
        res as retrofit2.Response<*>
        response = if (res.isSuccessful) {
          Success(res)
        } else {
          val errorResponse = getError(res)
          GenericError(res.code(), errorResponse)
        }
      } catch (throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {
          is IOException -> NetworkError
          is HttpException -> {
            val code = throwable.code()
            val err = throwable.response()?.errorBody()?.charStream()
            response = GenericError(code, err.toString())
          }
          else -> {
            Timber.log(3, throwable)
            response = GenericError(
              SOMETHING_WENT_WRONG_CODE,
              SOMETHING_WENT_WRONG
            )
          }
        }
      }
      return response
    }

    private fun getError(res: retrofit2.Response<*>): String {
      return if (res.errorBody() != null) {
        res.errorBody()!!.string()
      } else {
        SOMETHING_WENT_WRONG
      }
    }
  }
}
