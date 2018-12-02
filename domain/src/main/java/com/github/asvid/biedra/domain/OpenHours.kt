package com.github.asvid.biedra.domain

import java.text.SimpleDateFormat
import java.util.*

data class OpenHours(
    val weekDay: TimeRange,
    val saturday: TimeRange,
    val sunday: TimeRange
)

fun openHours(block: OpenHoursBuilder.() -> Unit): OpenHours = OpenHoursBuilder().apply(block).build()

@ShopDsl
class OpenHoursBuilder {
  var weekDay = ""
  var saturday = ""
  var sunday = ""

  fun build(): OpenHours = OpenHours(weekDay, saturday, sunday)
}

fun String.toOpenHours(): Pair<Date, Date> {

  val splitted = this.split(" - ".toRegex())
  val startDate = splitted[0].toDate("hh.mm")
  val endDate = splitted[1].toDate("hh.mm")

  return Pair(startDate, endDate)
}

fun String.toDate(format: String): Date {
  val formatter = SimpleDateFormat(format)
  return formatter.parse(this)
}