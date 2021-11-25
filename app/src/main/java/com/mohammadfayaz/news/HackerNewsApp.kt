package com.mohammadfayaz.news

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HackerNewsApp : Application() {
  override fun onCreate() {
    super.onCreate()
  }
}