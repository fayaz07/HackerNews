package com.mohammadfayaz.hn.domain.usecases.favourites

import com.mohammadfayaz.hn.data.sources.local.source.FavouritesLocalSource
import com.mohammadfayaz.hn.domain.models.FavouriteModel
import com.mohammadfayaz.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(private val favLocalSource: FavouritesLocalSource) :
  BaseUseCase() {
  suspend fun invoke(): List<FavouriteModel> {
    return favLocalSource.getAll()
  }
}
