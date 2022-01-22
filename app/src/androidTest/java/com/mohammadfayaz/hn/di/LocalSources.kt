package com.mohammadfayaz.hn.di

import com.mohammadfayaz.hn.data.sources.local.dao.IdsDao
import com.mohammadfayaz.hn.data.sources.local.source.IdsLocalSource
import com.mohammadfayaz.hn.utils.DbConstants.FAKE_IDS_DAO
import com.mohammadfayaz.hn.utils.SourceConstants.FAKE_LOCAL_IDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object LocalSources {

  @Provides
  @Named(FAKE_LOCAL_IDS)
  fun provideIdsLocalSource(@Named(FAKE_IDS_DAO) idsDao: IdsDao) = IdsLocalSource(idsDao)

}
