package com.mohammadfayaz.hn.data.sources.local.source

import com.mohammadfayaz.hn.data.sources.local.dao.StoryDao
import com.mohammadfayaz.hn.domain.models.StoryModel
import com.mohammadfayaz.hn.domain.models.StoryType
import com.mohammadfayaz.hn.utils.AppDateTimeUtils
import timber.log.Timber
import javax.inject.Inject

class StoriesLocalSource @Inject constructor(private val storyDao: StoryDao) : BaseLocalSource() {
  suspend fun put(item: StoryModel, type: StoryType) {
    item.storyType = type
    item.setDefaults()
    item.title = item.title?.replace("Show HN: ", "")
      ?.replace("Ask HN: ", "")
    if (item.time != null) {
      item.time = item.time!! * AppDateTimeUtils.DEF_TIME_MULTIPLY
    } else {
      item.time = System.currentTimeMillis()
    }
    Timber.d("Storing story in localdb")
    storyDao.insert(item)
  }

  suspend fun getById(id: Int): StoryModel? {
    return storyDao.getById(id)
  }
}
