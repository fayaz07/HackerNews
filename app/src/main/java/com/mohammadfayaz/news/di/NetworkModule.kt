package com.mohammadfayaz.news.di

import com.google.gson.Gson
import com.mohammadfayaz.news.network.GsonInstance
import com.mohammadfayaz.news.network.RetrofitInstance
import com.mohammadfayaz.news.network.api.HackerNewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideGsonInstance() = GsonInstance.instance()

  @Provides
  @Singleton
  fun provideOkHttpClient() = RetrofitInstance.buildOkHttpClient()

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient, gsonInstance: Gson) =
    RetrofitInstance.build(okHttpClient, gsonInstance)

  @Provides
  @Singleton
  fun hackerNewsAPI(retrofit: Retrofit): HackerNewsAPI =
    retrofit.create(HackerNewsAPI::class.java)
}
