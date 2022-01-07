package com.mohammadfayaz.hn.di

import com.mohammadfayaz.hn.data.sources.network.api.HackerNewsAPI
import com.mohammadfayaz.hn.data.sources.network.source.IdsNetworkSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkSourceModule {
  @Provides
  @Singleton
  fun provideIdsNetworkSource(api: HackerNewsAPI): IdsNetworkSource {
    return IdsNetworkSource(api)
  }
}
