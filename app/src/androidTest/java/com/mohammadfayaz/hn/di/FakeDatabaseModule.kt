package com.mohammadfayaz.hn.di

import android.content.Context
import com.mohammadfayaz.hn.data.sources.local.HackerNewsDB
import com.mohammadfayaz.hn.data.sources.local.dao.CommentsDao
import com.mohammadfayaz.hn.data.sources.local.dao.FavouritesDao
import com.mohammadfayaz.hn.data.sources.local.dao.IdsDao
import com.mohammadfayaz.hn.data.sources.local.dao.StoryDao
import com.mohammadfayaz.hn.utils.DbConstants.FAKE_COMMENTS_DAO
import com.mohammadfayaz.hn.utils.DbConstants.FAKE_DB
import com.mohammadfayaz.hn.utils.DbConstants.FAKE_FAVOURITES_DAO
import com.mohammadfayaz.hn.utils.DbConstants.FAKE_IDS_DAO
import com.mohammadfayaz.hn.utils.DbConstants.FAKE_STORY_DAO
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
object FakeDatabaseModule {

  @Provides
  @Named(FAKE_DB)
  fun provideDatabase(@ApplicationContext context: Context) =
    HackerNewsDB.getInstance(context, true)

  @Provides
  @Named(FAKE_STORY_DAO)
  fun provideStoryDao(@Named(FAKE_DB) db: HackerNewsDB): StoryDao = db.storyDao()

  @Provides
  @Named(FAKE_IDS_DAO)
  fun provideIdsDao(@Named(FAKE_DB) db: HackerNewsDB): IdsDao = db.idsDao()

  @Provides
  @Named(FAKE_FAVOURITES_DAO)
  fun provideFavouritesDao(@Named(FAKE_DB) db: HackerNewsDB): FavouritesDao = db.favouritesDao()

  @Provides
  @Named(FAKE_COMMENTS_DAO)
  fun provideCommentsDao(@Named(FAKE_DB) db: HackerNewsDB): CommentsDao = db.commentsDao()
}
