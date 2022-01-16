package com.mohammadfayaz.hn.data.sources.local.source

import com.mohammadfayaz.hn.data.sources.local.dao.CommentsDao
import javax.inject.Inject

class CommentsLocalSource @Inject constructor(private val commentsDao: CommentsDao) :
  BaseLocalSource() {
}
