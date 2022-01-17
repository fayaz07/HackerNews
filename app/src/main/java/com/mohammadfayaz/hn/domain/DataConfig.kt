package com.mohammadfayaz.hn.domain

object DataConfig {
  const val START_PAGE_INDEX = 1
  const val MAX_ITEMS_LIMIT = 5
  const val PRE_FETCH_DISTANCE = 5
  const val MAX_PAGE_SIZE = (MAX_ITEMS_LIMIT + 2 * PRE_FETCH_DISTANCE) * 2
  const val MIN_WAIT_TIME_FETCHING_IDS = 5
}
