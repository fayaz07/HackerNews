package com.mohammadfayaz.hn.ui.stories.ask.detail

import com.mohammadfayaz.hn.data.repository.CommentsRepo
import com.mohammadfayaz.hn.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AskStoryDetailViewModel @Inject constructor(private val repo: CommentsRepo) :
  BaseViewModel() {
  fun getComments(ids: List<Int>) = repo.getPaginatedFlow(ids)
}
