package com.mohammadfayaz.hn.data.sources.local.source

import com.mohammadfayaz.hn.data.sources.local.dao.CommentsDao
import com.mohammadfayaz.hn.domain.models.CommentModel
import com.mohammadfayaz.hn.utils.AppDateTimeUtils.DEF_TIME_MULTIPLY
import javax.inject.Inject

class CommentsLocalSource @Inject constructor(private val dao: CommentsDao) :
  BaseLocalSource() {
  suspend fun storeCommentInDb(item: CommentModel) {
    item.setDefaults()
    if (item.time != null) {
      item.time = item.time!! * DEF_TIME_MULTIPLY
    } else {
      item.time = System.currentTimeMillis()
    }
    dao.insert(item)
  }

  suspend fun getCommentFromDb(id: Int): CommentModel? {
    return dao.getById(id)
  }
}
