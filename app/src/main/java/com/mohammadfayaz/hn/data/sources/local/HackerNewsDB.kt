package com.mohammadfayaz.hn.data.sources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohammadfayaz.hn.data.sources.local.converters.ListConverters
import com.mohammadfayaz.hn.data.sources.local.dao.IdsDao
import com.mohammadfayaz.hn.data.sources.local.dao.StoryDao
import com.mohammadfayaz.hn.domain.models.StoryIdModel
import com.mohammadfayaz.hn.domain.models.StoryModel

@Database(
  entities = [StoryModel::class, StoryIdModel::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(ListConverters::class)
abstract class HackerNewsDB : RoomDatabase() {

  abstract fun storyDao(): StoryDao

  abstract fun idsDao(): IdsDao

  companion object {

    private const val db = "HackerNews.db"
    const val idsTable = "ids"
    const val storiesTable = "stories"

    fun getInstance(context: Context): HackerNewsDB {
      return Room.databaseBuilder(
        context.applicationContext,
        HackerNewsDB::class.java,
        db
      )
        .build()
    }
  }
}
