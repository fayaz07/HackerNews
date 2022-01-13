package com.mohammadfayaz.hn.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.mohammadfayaz.hn.domain.models.FavouriteModel
import com.mohammadfayaz.hn.domain.models.StoryType

@Dao
interface FavouritesDao : BaseDao<FavouriteModel> {
  @Query("SELECT * FROM favourites WHERE type = :type ORDER BY savedOn DESC")
  suspend fun getAllByType(type: StoryType): List<FavouriteModel>

  @Query("SELECT * FROM favourites WHERE id = :id")
  suspend fun getById(id: Int): FavouriteModel?
}
