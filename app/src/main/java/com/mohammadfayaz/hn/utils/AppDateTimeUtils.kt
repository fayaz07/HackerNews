package com.mohammadfayaz.hn.utils

import org.joda.time.format.DateTimeFormat

object AppDateTimeUtils {
  fun formatDate(time: Long?): String {
    if (time != null) {
      val format = DateTimeFormat.forPattern("dd-MM-yyyy")
      return format.print(time)
    }
    return ""
  }
}
