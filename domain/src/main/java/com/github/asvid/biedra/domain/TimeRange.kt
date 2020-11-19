package com.github.asvid.biedra.domain

import java.time.LocalTime

/**
 * [TimeRange] allows easer storing and manipulating time ranges, for example: [Shop] open hours
 * This class uses [Duration] and [LocalTime] from JodaTime library, because similar Java classes are available from API26
 *
 * @property start beginning of range
 * @property end end of range
 * */
class TimeRange(val start: LocalTime, val end: LocalTime) {

    /**
     * @param time [LocalTime]
     *
     * @return returns true if provided [time] is equal or after [start] and before or equal [end]
     * */
    fun withinRange(time: LocalTime) = (time.isAfter(start) || time == start) && (time.isBefore(end) || time == end)
}
