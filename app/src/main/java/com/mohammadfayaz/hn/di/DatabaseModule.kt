package com.mohammadfayaz.hn.di

import android.content.Context
import com.mohammadfayaz.hn.data.sources.local.HackerNewsDB
import com.mohammadfayaz.hn.data.sources.local.dao.CommentsDao
import com.mohammadfayaz.hn.data.sources.local.dao.FavouritesDao
import com.mohammadfayaz.hn.data.sources.local.dao.IdsDao
import com.mohammadfayaz.hn.data.sources.local.dao.StoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
  @Provides
  fun provideDatabase(@ApplicationContext context: Context) =
    HackerNewsDB.getInstance(context)

  @Provides
  fun provideStoryDao(db: HackerNewsDB): StoryDao = db.storyDao()

  @Provides
  fun provideIdsDao(db: HackerNewsDB): IdsDao = db.idsDao()

  @Provides
  fun provideFavouritesDao(db: HackerNewsDB): FavouritesDao = db.favouritesDao()

  @Provides
  fun provideCommentsDao(db: HackerNewsDB): CommentsDao = db.commentsDao()
}
