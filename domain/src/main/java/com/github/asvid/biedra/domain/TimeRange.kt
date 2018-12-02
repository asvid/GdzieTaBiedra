package com.github.asvid.biedra.domain

import org.joda.time.Duration
import org.joda.time.LocalTime
import java.util.*
import java.util.concurrent.TimeUnit

class TimeRange(val start: LocalTime, val end: LocalTime) {

  constructor(start: LocalTime, length: Duration) : this(start, start.plus(length.toPeriod()))

  constructor(startDate: Date, endDate: Date) : this(LocalTime(startDate), LocalTime(endDate))

  private val duration: Duration
    get() {
      val duration = Duration(start.millisOfDay.toLong(), end.millisOfDay.toLong())
      return if (duration.millis < 0) {
        duration.plus(Duration(TimeUnit.DAYS.toMillis(1)))
      } else duration
    }

  fun withinRange(time: LocalTime) = (time.isAfter(start) || time.isEqual(start)) && (time.isBefore(end) || time.isEqual(end))

  fun toHours() = duration.toStandardHours().hours

  fun toMinutes() = duration.toStandardMinutes().minutes

  override fun toString() = "${start.toString("hh:mm")} - ${end.toString("hh:mm")}"
}
