package com.mohammadfayaz.news.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohammadfayaz.news.data.db.converters.ListConverters
import com.mohammadfayaz.news.data.models.ShowStoryModel
import com.mohammadfayaz.news.utils.AppConstants

@Database(
  entities = [ShowStoryModel::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(
  *[ListConverters::class]
)
abstract class HackerNewsDB : RoomDatabase() {
  companion object {
    fun getInstance(context: Context): HackerNewsDB {
      return Room.databaseBuilder(
        context.applicationContext,
        HackerNewsDB::class.java,
        AppConstants.db
      )
        .build()
    }
  }
}
