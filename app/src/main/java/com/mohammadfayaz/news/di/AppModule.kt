package com.mohammadfayaz.news.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, RepositoryModule::class])
@InstallIn(SingletonComponent::class)
class AppModule {

}
