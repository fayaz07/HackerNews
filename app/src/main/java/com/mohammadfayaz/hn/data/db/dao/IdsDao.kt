package com.mohammadfayaz.hn.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.mohammadfayaz.hn.data.models.StoryIdModel
import com.mohammadfayaz.hn.data.models.StoryType

@Dao
interface IdsDao : BaseDao<StoryIdModel> {
  @Query("SELECT * FROM ids WHERE type = :type ORDER BY birth DESC")
  suspend fun getAllIdsByType(type: StoryType): List<StoryIdModel>
}
