package com.mohammadfayaz.hn.data.repository

import com.mohammadfayaz.hn.domain.DataConfig.MIN_WAIT_TIME_FETCHING_IDS
import com.mohammadfayaz.hn.domain.models.StoryType
import java.util.concurrent.TimeUnit

object AppCache {
  private var fetchTrack: MutableMap<String, Long> = HashMap()

  fun canFetch(storyType: StoryType): Boolean {
    val lastFetch = fetchTrack[storyType.string]
    if (lastFetch != null) {
      val current = System.currentTimeMillis()
      val diff = current - lastFetch
      if (TimeUnit.MILLISECONDS.toMinutes(diff) < MIN_WAIT_TIME_FETCHING_IDS) {
        return false
      }
    }
    fetchTrack[storyType.string] = System.currentTimeMillis()
    return true
  }
}
