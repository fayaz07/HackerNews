package com.mohammadfayaz.hn.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.mohammadfayaz.hn.domain.models.StoryModel

@Dao
interface StoryDao : BaseDao<StoryModel> {
  @Query("SELECT * FROM stories WHERE id = :id")
  suspend fun getById(id: Int): StoryModel?
}
