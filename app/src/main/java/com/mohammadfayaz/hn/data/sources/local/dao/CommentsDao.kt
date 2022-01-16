package com.mohammadfayaz.hn.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.mohammadfayaz.hn.domain.models.CommentModel

@Dao
interface CommentsDao : BaseDao<CommentModel> {
  @Query("SELECT * FROM comments WHERE parent = :storyId ORDER BY time DESC")
  suspend fun getByStoryId(storyId: Int): List<CommentModel>
}
