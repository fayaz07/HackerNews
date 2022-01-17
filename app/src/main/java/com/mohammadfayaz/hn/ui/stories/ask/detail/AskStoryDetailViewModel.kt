package com.mohammadfayaz.hn.ui.stories.ask.detail

import com.mohammadfayaz.hn.domain.usecases.comments.CommentsPaginationUseCase
import com.mohammadfayaz.hn.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AskStoryDetailViewModel @Inject constructor(private val useCase: CommentsPaginationUseCase) :
  BaseViewModel() {
  fun getComments(ids: List<Int>) = useCase.invoke(ids)
}
