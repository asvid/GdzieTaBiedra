package com.github.asvid.biedra.domain

import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

/**
 * [TimeRange] allows easer storing and manipulating time ranges, for example: [Shop] open hours
 * This class uses [Duration] and [LocalTime]
 *
 * @property start beginning of range
 * @property end end of range
 * */
class TimeRange(val start: LocalTime, val end: LocalTime) {

    /**
     * @constructor that takes standard Java [Date] start and [Date] end
     *
     * @param startDate start date of range
     * @param endDate end date of range
     * */
    constructor(startDate: Date, endDate: Date) : this(
            LocalTime.of(startDate.hours, startDate.minutes),
            LocalTime.of(endDate.hours, endDate.minutes))

    /**
     * @return [String] representation of range in 24h format without seconds: <start> - <end>
     * */
    override fun toString(): String {
        val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.getDefault())
        return "${start.format(formatter)} - ${end.format(formatter)}"
    }

    fun withinRange(inRange: LocalTime?): Boolean {
        return this.start.isBefore(inRange) && this.end.isAfter(inRange)
    }
}
