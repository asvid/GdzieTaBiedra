package com.github.asvid.biedra.domain

import org.joda.time.Duration
import org.joda.time.LocalTime
import java.util.concurrent.TimeUnit

data class TimeRange(val start: LocalTime, val end: LocalTime) {

  constructor(start: LocalTime, length: Duration) : this(start, start.plus(length.toPeriod()))

  private val duration: Duration
    get() {
      val duration = Duration(start.millisOfDay.toLong(), end.millisOfDay.toLong())
      return if (duration.millis < 0) {
        duration.plus(Duration(TimeUnit.DAYS.toMillis(1)))
      } else duration
    }

  fun overlaps(other: TimeRange): Boolean {
    if (endsNextDay()) {
      return overlapsDayEnd(other)
    }
    if (other.endsNextDay()) {
      return other.overlapsDayEnd(this)
    }
    assert(start.isBefore(end)) { "start '$start' must be less than end '$end'!" }
    assert(other.start.isBefore(other.end)) { "other start must be less than other end!" }
    return !start.isAfter(other.end) && !end.isBefore(other.start)
  }

  private fun overlapsDayEnd(other: TimeRange): Boolean {
    // check rest of this day and start of next day separately
    val firstPart = TimeRange(start, LocalTime.MIDNIGHT)
    val secondPart = TimeRange(LocalTime.MIDNIGHT, end)
    return firstPart.overlaps(other) || secondPart.duration.millis == 0L || secondPart.overlaps(other)
  }

  /**
   * @return `true` if the end of this time range is an the next day.
   */
  fun endsNextDay(): Boolean {
    return start.isAfter(end)
  }

  fun toHours(): Int {
    return duration.toStandardHours().hours
  }

  fun toMinutes(): Int {
    return duration.toStandardMinutes().minutes
  }
}