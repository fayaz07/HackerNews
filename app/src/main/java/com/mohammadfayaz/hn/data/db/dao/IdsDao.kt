package com.mohammadfayaz.hn.data.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mohammadfayaz.hn.data.models.StoryIdModel
import com.mohammadfayaz.hn.data.models.StoryType

interface IdsDao : BaseDao<StoryIdModel> {
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  override suspend fun insertAll(list: List<StoryIdModel>)

  @Query("SELECT * FROM ids WHERE type = :type")
  suspend fun getAllIdsByType(type: StoryType): List<StoryIdModel>
}
