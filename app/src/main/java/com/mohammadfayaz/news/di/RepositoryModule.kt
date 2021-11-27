package com.mohammadfayaz.news.di

import com.mohammadfayaz.news.network.api.HackerNewsAPI
import com.mohammadfayaz.news.repository.HackerNewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

  @Provides
  @Singleton
  fun hackerNewsRepo(api: HackerNewsAPI) = HackerNewsRepo(api)

}
