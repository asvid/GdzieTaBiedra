package com.github.asvid.biedra.domain

import org.joda.time.DateTimeConstants
import org.joda.time.LocalDate
import java.time.LocalTime
import java.util.*

/**
 * [Shop] open hours, contains [TimeRange] for weekdays, saturday and sunday
 *
 * @property weekDay opening hours for monday - friday
 * @property saturday opening hours
 * @property sunday opening hours
 * */
data class OpenHours(
        val weekDay: TimeRange?,
        val saturday: TimeRange?,
        val sunday: TimeRange?
)

/**
 * DLS method for [OpenHours]
 * */
fun openHours(block: OpenHoursBuilder.() -> Unit): OpenHours = OpenHoursBuilder().apply(
        block).build()

/**
 * DSL builder for [OpenHours]
 *
 * @property weekDay
 * @property saturday
 * @property sunday
 * */
@ShopDsl
class OpenHoursBuilder {
    var weekDay: String? = null
    var saturday: String? = null
    var sunday: String? = null

    fun build(): OpenHours = OpenHours(weekDay?.toOpenHours(), saturday?.toOpenHours(),
            sunday?.toOpenHours())
}

/**
 * @receiver [String] with range of hours in format: <hh.mm> - <hh.mm>
 *
 * @return [TimeRange] created from [String] range
 * */
fun String.toOpenHours(): TimeRange? {

    val splitted = this.split("-".toRegex())

    if (splitted.size != 2) {
        println("Wrong opening hours format: $this")
        return null
    }
    val startTime = splitted[0].toTime()
    val endTime = splitted[1].toTime()
    if (startTime == null || endTime == null) {
        println("couldn't parse start or end dates of: $this")
        return null
    }

    return TimeRange(startTime, endTime)
}

/**
 * @receiver [String] with date in any possible to process format
 *
 * @param format of date in receiver [String]
 *
 * @return [Date] from parsed [String] with provided [format]
 * */
fun String.toTime(): LocalTime? {
    val splited = this.split(":", ".")
    if (splited.size != 2) return null;
    val hour = splited[0].trim().toInt()
    val minute = splited[1].trim().toInt()
    return LocalTime.of(hour, minute)
}

fun OpenHours.getForToday(): TimeRange? =
        when (LocalDate().dayOfWeek) {
            DateTimeConstants.SUNDAY -> this.sunday ?: this.weekDay
            DateTimeConstants.SATURDAY -> this.saturday ?: this.weekDay
            else -> this.weekDay
        }
