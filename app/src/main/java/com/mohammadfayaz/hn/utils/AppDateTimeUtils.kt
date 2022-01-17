package com.mohammadfayaz.hn.utils

import org.joda.time.format.DateTimeFormat

object AppDateTimeUtils {

  const val DEF_TIME_MULTIPLY: Long = 1000L

  fun formatDate(time: Long?): String {
    if (time != null) {
      val format = DateTimeFormat.forPattern("dd-MM-yyyy")
      return format.print(time)
    }
    return ""
  }
}
