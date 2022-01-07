package com.mohammadfayaz.hn.di

import com.mohammadfayaz.hn.data.repository.IdsRepo
import com.mohammadfayaz.hn.data.repository.ShowStoriesRepo
import com.mohammadfayaz.hn.data.sources.local.source.IdsLocalSource
import com.mohammadfayaz.hn.data.sources.local.source.StoriesLocalSource
import com.mohammadfayaz.hn.data.sources.network.source.IdsNetworkSource
import com.mohammadfayaz.hn.data.sources.network.source.StoriesNetworkSource
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
  fun showStoriesRepo(
    storiesNetworkSource: StoriesNetworkSource,
    storiesLocalSource: StoriesLocalSource
  ) =
    ShowStoriesRepo(storiesNetworkSource, storiesLocalSource)

  @Provides
  @Singleton
  fun showIdsRepo(
    idsLocalSource: IdsLocalSource,
    idsNetworkSource: IdsNetworkSource
  ) =
    IdsRepo(idsLocalSource, idsNetworkSource)
}
