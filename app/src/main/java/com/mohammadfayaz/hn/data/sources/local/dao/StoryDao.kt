package com.mohammadfayaz.hn.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.mohammadfayaz.hn.domain.models.StoryModel

@Dao
interface StoryDao : BaseDao<StoryModel> {
  @Query("SELECT * FROM stories WHERE id = :id")
  suspend fun getById(id: Int): StoryModel?
}
