package com.mohammadfayaz.news.di

import com.google.gson.Gson
import com.mohammadfayaz.news.network.GsonInstance
import com.mohammadfayaz.news.network.RetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideGsonInstance() = GsonInstance.instance()

  @Provides
  @Singleton
  fun provideOkHttpClient() = RetrofitAPI.buildOkHttpClient()

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient, gsonInstance: Gson) =
    RetrofitAPI.build(okHttpClient, gsonInstance)
}
