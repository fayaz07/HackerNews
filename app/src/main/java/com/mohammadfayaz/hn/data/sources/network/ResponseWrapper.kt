package com.mohammadfayaz.hn.data.sources.network

import okio.IOException
import retrofit2.HttpException
import timber.log.Timber

sealed class ResponseWrapper<out T> {
  data class Success<out T>(val value: T) : ResponseWrapper<T>()
  data class GenericError(val code: Int, val error: String) :
    ResponseWrapper<Nothing>()

  object NetworkError : ResponseWrapper<Nothing>()

  companion object {

    private const val safeApiCall = "safeApiCall"

    private const val SOMETHING_WENT_WRONG: String = "Something went wrong"
    private const val SOMETHING_WENT_WRONG_CODE: Int = 500

    @Suppress("SwallowedException", "TooGenericExceptionCaught")
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResponseWrapper<T> {
      var response: ResponseWrapper<T>
      try {
        val res = apiCall.invoke()
        res as retrofit2.Response<*>
        response = if (res.isSuccessful) {
          Success(res)
        } else {
          val errorResponse = getError(res)
          Timber.tag(safeApiCall).d("Generic Error- $errorResponse")
          GenericError(res.code(), errorResponse)
        }
      } catch (httpEx: HttpException) {
        Timber.tag(safeApiCall).d("Http Exception- ")
        val code = httpEx.code()
        val err = httpEx.response()?.errorBody()?.charStream()
        response = GenericError(code, err.toString())
      } catch (throwable: Throwable) {
        Timber.tag(safeApiCall).d("Some Generic Error- ")
        response = GenericError(
          SOMETHING_WENT_WRONG_CODE,
          SOMETHING_WENT_WRONG
        )
      } catch (ioEx: IOException) {
        Timber.tag(safeApiCall).d("Network Error - ")
        response = NetworkError
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
