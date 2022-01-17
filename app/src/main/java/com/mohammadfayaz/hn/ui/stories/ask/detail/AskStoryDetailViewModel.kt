package com.mohammadfayaz.hn.ui.stories.ask.detail

import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.hn.domain.models.StoryType
import com.mohammadfayaz.hn.domain.usecases.favourites.CheckIsFavouriteUseCase
import com.mohammadfayaz.hn.domain.usecases.favourites.SaveFavouriteUseCase
import com.mohammadfayaz.hn.ui.base.BaseViewModel
import com.mohammadfayaz.hn.ui.stories.ask.detail.AskStoryDetailFragment.Companion.FAVOURITE_EVENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AskStoryDetailViewModel @Inject constructor(
  private val isFavouriteUseCase: CheckIsFavouriteUseCase,
  private val saveFavouriteUseCase: SaveFavouriteUseCase
) : BaseViewModel() {
  fun isFavourite(id: Int) {
    viewModelScope.launch {
      val isFav = isFavouriteUseCase.invoke(id)
      postSuccess(isFav, "", FAVOURITE_EVENT)
    }
  }

  fun saveUnSaveAsFavourite(id: Int, storyType: StoryType) {
    viewModelScope.launch {
      val isFav = saveFavouriteUseCase.invoke(id, storyType)
      postSuccess(isFav, "", FAVOURITE_EVENT)
    }
  }
}
