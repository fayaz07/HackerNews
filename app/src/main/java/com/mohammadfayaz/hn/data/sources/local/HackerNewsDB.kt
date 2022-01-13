package com.mohammadfayaz.hn.data.sources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mohammadfayaz.hn.data.sources.local.converters.ListConverters
import com.mohammadfayaz.hn.data.sources.local.dao.FavouritesDao
import com.mohammadfayaz.hn.data.sources.local.dao.IdsDao
import com.mohammadfayaz.hn.data.sources.local.dao.StoryDao
import com.mohammadfayaz.hn.domain.models.FavouriteModel
import com.mohammadfayaz.hn.domain.models.StoryIdModel
import com.mohammadfayaz.hn.domain.models.StoryModel

@Database(
  entities = [StoryModel::class, StoryIdModel::class, FavouriteModel::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(ListConverters::class)
abstract class HackerNewsDB : RoomDatabase() {

  abstract fun storyDao(): StoryDao

  abstract fun idsDao(): IdsDao

  abstract fun favouritesDao(): FavouritesDao

  companion object {

    private const val db = "HackerNews.db"
    const val idsTable = "ids"
    const val favouritesTable = "favourites"
    const val storiesTable = "stories"

    fun getInstance(context: Context, isInMemory: Boolean = false): HackerNewsDB {
      return if (isInMemory) {
        Room.inMemoryDatabaseBuilder(context.applicationContext, HackerNewsDB::class.java)
          .allowMainThreadQueries().build()
      } else Room.databaseBuilder(
        context.applicationContext,
        HackerNewsDB::class.java,
        db
      )
        .build()
    }
  }
}
