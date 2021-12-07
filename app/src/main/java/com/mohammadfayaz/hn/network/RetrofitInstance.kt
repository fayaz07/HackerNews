package com.mohammadfayaz.hn.network

import com.google.gson.Gson
import com.mohammadfayaz.hn.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

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

    fun build(okHttpClient: OkHttpClient, gsonInstance: Gson): Retrofit {
//      println(BuildConfig.hnBaseUrl + BuildConfig.hnApiVersion)
      return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.hnBaseUrl + BuildConfig.hnApiVersion)
        .addConverterFactory(GsonConverterFactory.create(gsonInstance))
        .build()
    }
  }
}
