package com.mohammadfayaz.hn.ui.stories.detail

import androidx.lifecycle.ViewModel
import com.mohammadfayaz.hn.domain.usecases.favourites.CheckIsFavouriteUseCase
import com.mohammadfayaz.hn.domain.usecases.favourites.SaveFavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryDetailedViewModel @Inject constructor(
  private val isFavouriteUseCase: CheckIsFavouriteUseCase,
  private val saveFavouriteUseCase: SaveFavouriteUseCase
) : ViewModel() {
  suspend fun isFavourite(id: Int) = isFavouriteUseCase.invoke(id)
}
