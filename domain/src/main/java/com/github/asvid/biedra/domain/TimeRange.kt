package com.github.asvid.biedra.domain

import org.joda.time.Duration
import org.joda.time.LocalTime
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * [TimeRange] allows easer storing and manipulating time ranges, for example: [Shop] open hours
 * This class uses [Duration] and [LocalTime] from JodaTime library, because similar Java classes are available from API26
 *
 * @property start beginning of range
 * @property end end of range
 * */
class TimeRange(val start: LocalTime, val end: LocalTime) {

  /**
   * @constructor that takes [LocalTime] start and [Duration] duration of range
   *
   * @param start start date of range
   * @param length duration of range
   * */
  constructor(start: LocalTime, length: Duration) : this(start, start.plus(length.toPeriod()))

  /**
   * @constructor that takes standard Java [Date] start and [Date] end
   *
   * @param startDate start date of range
   * @param endDate end date of range
   * */
  constructor(startDate: Date, endDate: Date) : this(LocalTime.fromDateFields(startDate), LocalTime.fromDateFields(endDate))

  private val duration: Duration
    get() {
      val duration = Duration(start.millisOfDay.toLong(), end.millisOfDay.toLong())
      return if (duration.millis < 0) {
        duration.plus(Duration(TimeUnit.DAYS.toMillis(1)))
      } else duration
    }

  /**
   * @param time [LocalTime]
   *
   * @return returns true if provided [time] is equal or after [start] and before or equal [end]
   * */
  fun withinRange(time: LocalTime) = (time.isAfter(start) || time.isEqual(start)) && (time.isBefore(end) || time.isEqual(end))

  /**
   * @return [Int] number of hours in range duration
   * */
  fun toHours() = duration.toStandardHours().hours

  /**
   * @return [Int] number of minutes in range duration
   * */
  fun toMinutes() = duration.toStandardMinutes().minutes

  /**
   * @return [String] representation of range in 24h format without seconds: <start> - <end>
   * */
  override fun toString() = "${start.toString("H:mm")} - ${end.toString("H:mm")}"
}
