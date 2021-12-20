package com.mohammadfayaz.hn.di

import com.mohammadfayaz.hn.data.db.dao.IdsDao
import com.mohammadfayaz.hn.data.db.dao.StoryDao
import com.mohammadfayaz.hn.domain.repository.ShowStoriesRepo
import com.mohammadfayaz.hn.network.api.HackerNewsAPI
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
  fun showStoriesRepo(api: HackerNewsAPI, dao: StoryDao, idsDao: IdsDao) =
    ShowStoriesRepo(api, dao, idsDao)
}
