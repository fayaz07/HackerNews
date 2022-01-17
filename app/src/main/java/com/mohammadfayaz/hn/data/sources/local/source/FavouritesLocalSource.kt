package com.mohammadfayaz.hn.data.sources.local.source

import com.mohammadfayaz.hn.data.sources.local.dao.FavouritesDao
import com.mohammadfayaz.hn.domain.models.FavouriteModel
import com.mohammadfayaz.hn.domain.models.StoryType
import javax.inject.Inject

class FavouritesLocalSource @Inject constructor(private val favouritesDao: FavouritesDao) :
  BaseLocalSource() {
  suspend fun isFavourite(id: Int): Boolean {
    val exists = favouritesDao.getById(id)
    return exists != null
  }

  suspend fun saveOrUnSave(id: Int, type: StoryType): Boolean {
    val fav = favouritesDao.getById(id)
    return if (fav != null) {
      favouritesDao.delete(fav)
      false
    } else {
      favouritesDao.insert(FavouriteModel(id, type))
      true
    }
  }

  suspend fun getAll() = favouritesDao.getAll()
}
