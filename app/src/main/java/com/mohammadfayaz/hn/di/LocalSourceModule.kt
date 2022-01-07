package com.mohammadfayaz.hn.di

import com.mohammadfayaz.hn.data.sources.local.dao.IdsDao
import com.mohammadfayaz.hn.data.sources.local.dao.StoryDao
import com.mohammadfayaz.hn.data.sources.local.source.IdsLocalSource
import com.mohammadfayaz.hn.data.sources.local.source.StoriesLocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModule {
  @Singleton
  @Provides
  fun provideIdsSource(idsDao: IdsDao): IdsLocalSource {
    return IdsLocalSource(idsDao)
  }

  @Singleton
  @Provides
  fun provideStoriesSource(storiesDao: StoryDao): StoriesLocalSource {
    return StoriesLocalSource(storiesDao)
  }
}
