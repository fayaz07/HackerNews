package com.mohammadfayaz.hn.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
  includes = [NetworkModule::class, DatabaseModule::class]
)
@InstallIn(SingletonComponent::class)
class AppModule
