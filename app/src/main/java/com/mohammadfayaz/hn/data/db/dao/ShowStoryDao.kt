package com.mohammadfayaz.hn.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.mohammadfayaz.hn.data.models.ShowStoryModel

@Dao
interface ShowStoryDao : BaseDao<ShowStoryModel> {
  @Query("SELECT * FROM showStories WHERE id = :id")
  suspend fun getById(id: Int): ShowStoryModel?
}
