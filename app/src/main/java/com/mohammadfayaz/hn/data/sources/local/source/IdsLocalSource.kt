package com.mohammadfayaz.hn.data.sources.local.source

import com.mohammadfayaz.hn.data.sources.local.dao.IdsDao
import com.mohammadfayaz.hn.domain.models.StoryIdModel
import com.mohammadfayaz.hn.domain.models.StoryType
import javax.inject.Inject

class IdsLocalSource @Inject constructor(private val idsDao: IdsDao) : BaseLocalSource() {
  suspend fun getIdsByStoryType(type: StoryType): List<StoryIdModel> {
    return idsDao.getAllIdsByType(type)
  }

  suspend fun putIds(list: List<StoryIdModel>) {
    idsDao.insertAll(list)
  }
}
