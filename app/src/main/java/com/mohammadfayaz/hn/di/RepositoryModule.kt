package com.mohammadfayaz.hn.di

import com.mohammadfayaz.hn.data.sources.local.source.IdsLocalSource
import com.mohammadfayaz.hn.data.sources.local.source.StoriesLocalSource
import com.mohammadfayaz.hn.data.sources.network.source.IdsNetworkSource
import com.mohammadfayaz.hn.data.sources.network.source.StoriesNetworkSource
import com.mohammadfayaz.hn.domain.repository.ShowStoriesRepo
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
    idsNetworkSource: IdsNetworkSource,
    storiesNetworkSource: StoriesNetworkSource,
    idsLocalSource: IdsLocalSource,
    storiesLocalSource: StoriesLocalSource
  ) =
    ShowStoriesRepo(idsNetworkSource, storiesNetworkSource, storiesLocalSource, idsLocalSource)
}
