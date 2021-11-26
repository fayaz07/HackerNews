package com.mohammadfayaz.news.network

import com.mohammadfayaz.news.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitAPI {

  companion object {

    private const val timeOutInSeconds = 15L

    fun buildOkHttpClient(): OkHttpClient {
      return OkHttpClient.Builder()
        .apply {
          if (BuildConfig.DEBUG) {
            addInterceptor(
              HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
              }
            )
          }
          connectTimeout(timeOutInSeconds, TimeUnit.SECONDS)
          writeTimeout(timeOutInSeconds, TimeUnit.SECONDS)
          readTimeout(timeOutInSeconds, TimeUnit.SECONDS)
        }
        .build()
    }

    fun build(okHttpClient: OkHttpClient): Retrofit {
      return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.hnBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
  }
}
