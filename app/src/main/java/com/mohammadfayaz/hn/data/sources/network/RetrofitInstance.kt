package com.mohammadfayaz.hn.data.sources.network

import android.net.Uri
import com.google.gson.Gson
import com.mohammadfayaz.hn.BuildConfig
import com.mohammadfayaz.hn.utils.AppConstants
import com.mohammadfayaz.hn.utils.exceptions.InvalidUrlException
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

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

    if (!Uri.parse(BuildConfig.hnBaseUrl).isAbsolute) {
      throw InvalidUrlException(AppConstants.INVALID_HN_API_URL)
    }

    if (BuildConfig.hnApiVersion.isBlank() ||
      !BuildConfig.hnApiVersion.contains("v")
    ) {
      throw InvalidUrlException(AppConstants.INVALID_HN_API_VERSION)
    }

    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(BuildConfig.hnBaseUrl + BuildConfig.hnApiVersion)
      .addConverterFactory(GsonConverterFactory.create(gsonInstance))
      .build()
  }
}
