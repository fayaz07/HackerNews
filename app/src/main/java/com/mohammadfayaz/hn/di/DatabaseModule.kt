package com.mohammadfayaz.hn.di

import android.content.Context
import com.mohammadfayaz.hn.data.db.HackerNewsDB
import com.mohammadfayaz.hn.data.db.dao.IdsDao
import com.mohammadfayaz.hn.data.db.dao.StoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context) =
    HackerNewsDB.getInstance(context)

  @Provides
  @Singleton
  fun provideStoryDao(db: HackerNewsDB): StoryDao = db.storyDao()

  @Provides
  @Singleton
  fun provideIdsDao(db: HackerNewsDB): IdsDao = db.idsDao()
}
