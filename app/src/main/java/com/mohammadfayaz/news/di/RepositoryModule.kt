package com.mohammadfayaz.news.di

import com.mohammadfayaz.news.data.db.dao.StoryDao
import com.mohammadfayaz.news.network.api.HackerNewsAPI
import com.mohammadfayaz.news.domain.repository.ShowStoriesRepo
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
  fun showStoriesRepo(api: HackerNewsAPI, dao: StoryDao) = ShowStoriesRepo(api, dao)

}
