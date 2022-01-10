package com.mohammadfayaz.hn.di

import android.content.Context
import com.mohammadfayaz.hn.data.sources.local.HackerNewsDB
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
object FakeDatabaseModule {

  @Provides
  @Singleton
  @Named("fake_db")
  fun provideDatabase(@ApplicationContext context: Context) =
    HackerNewsDB.getInstance(context, true)
}
