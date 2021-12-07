package com.mohammadfayaz.hn.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohammadfayaz.hn.data.db.converters.ListConverters
import com.mohammadfayaz.hn.data.db.dao.ShowStoryDao
import com.mohammadfayaz.hn.data.db.dao.StoryDao
import com.mohammadfayaz.hn.data.models.ShowStoryModel
import com.mohammadfayaz.hn.data.models.StoryModel
import com.mohammadfayaz.hn.utils.AppConstants

@Database(
  entities = [ShowStoryModel::class, StoryModel::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(
  *[ListConverters::class]
)
abstract class HackerNewsDB : RoomDatabase() {

  abstract fun showStoryDao(): ShowStoryDao

  abstract fun storyDao(): StoryDao

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
