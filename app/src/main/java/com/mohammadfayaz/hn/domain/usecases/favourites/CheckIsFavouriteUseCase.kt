package com.mohammadfayaz.hn.domain.usecases.favourites

import com.mohammadfayaz.hn.data.sources.local.source.FavouritesLocalSource
import com.mohammadfayaz.hn.domain.usecases.BaseUseCase
import javax.inject.Inject

class CheckIsFavouriteUseCase @Inject constructor(private val local: FavouritesLocalSource) :
  BaseUseCase() {
  suspend fun invoke(id: Int): Boolean {
    return local.isFavourite(id)
  }
}
