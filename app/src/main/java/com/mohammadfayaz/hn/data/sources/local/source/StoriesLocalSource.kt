package com.mohammadfayaz.hn.data.sources.local.source

import com.mohammadfayaz.hn.data.sources.local.dao.StoryDao
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.models.StoryType
import timber.log.Timber

class StoriesLocalSource(private val storyDao: StoryDao) : BaseLocalSource() {
  suspend fun put(item: StoryModel, type: StoryType) {
    item.storyType = type
    item.setDefaults()
    item.title = item.title?.replace("Show HN: ", "")
    Timber.d("Storing story in localdb")
    storyDao.insert(item)
  }

  suspend fun getById(id: Int): StoryModel? {
    return storyDao.getById(id)
  }
}
