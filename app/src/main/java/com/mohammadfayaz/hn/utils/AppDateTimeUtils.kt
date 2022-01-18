package com.mohammadfayaz.hn.utils

object AppDateTimeUtils {

  private const val ONE_SECOND = 1000L
  private const val ONE_MIN: Long = 60 * ONE_SECOND

  private const val ONE_HOUR: Long = 60 * ONE_MIN
  private const val TWO_HOURS: Long = 2 * ONE_HOUR

  private const val ONE_DAY: Long = 24 * ONE_HOUR
  private const val TWO_DAYS: Long = 2 * ONE_DAY

  private const val ONE_WEEK: Long = 7 * ONE_DAY
  private const val TWO_WEEKS: Long = 2 * ONE_WEEK

  private const val ONE_MONTH: Long = 30 * ONE_DAY
  private const val TWO_MONTHS: Long = 2 * ONE_MONTH
  private const val ONE_YEAR: Long = 365 * ONE_DAY
  private const val TWO_YEARS: Long = 2 * ONE_YEAR

  const val DEF_TIME_MULTIPLY: Long = ONE_SECOND

  // TODO: need to think of a better algorithm
  fun whenDidThisHappen(time: Long?): String {
    if (time != null) {

      val diff: Long = System.currentTimeMillis() - time

      val result: String
      if (diff <= ONE_MIN) {
        result = "1 min"
      } else if (diff < ONE_HOUR) {
        val howManyMins = diff / ONE_MIN
        result = "$howManyMins mins"
      } else if (diff in (ONE_HOUR + 1) until TWO_HOURS) {
        result = "1 hr"
      } else if (diff in (ONE_HOUR + 1) until ONE_DAY) {
        val howManyHours = diff / ONE_HOUR
        result = "$howManyHours hrs"
      } else if (diff in (ONE_DAY + 1) until TWO_DAYS) {
        result = "1 day"
      } else if (diff in (ONE_DAY + 1) until ONE_WEEK) {
        val howManyDays = diff / ONE_DAY
        result = "$howManyDays days"
      } else if (diff in (ONE_WEEK + 1) until TWO_WEEKS) {
        result = "1 week"
      } else if (diff in (TWO_WEEKS) until ONE_MONTH) {
        val howManyWeeks = diff / ONE_WEEK
        result = "$howManyWeeks weeks"
      } else if (diff in (ONE_MONTH + 1) until TWO_MONTHS) {
        result = "1 month"
      } else if (diff in (TWO_MONTHS) until ONE_YEAR) {
        val howManyMonths = diff / ONE_MONTH
        result = "$howManyMonths months"
      } else if (diff < ONE_YEAR && diff < TWO_YEARS) {
        result = "1 year"
      } else {
        val howManyYears = diff / ONE_YEAR
        result = "$howManyYears years"
      }
      return result
//      val format = DateTimeFormat.forPattern("dd-MM-yyyy")
//      return format.print(time)
    }
    return ""
  }
}
