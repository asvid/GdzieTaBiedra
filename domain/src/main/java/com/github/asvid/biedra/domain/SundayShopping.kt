package com.github.asvid.biedra.domain

import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import java.util.*

object SundayShopping {
    val businessDays = listOf(
            LocalDate(2018, 12, 2),
            LocalDate(2018, 12, 16),
            LocalDate(2018, 12, 23),
            LocalDate(2018, 12, 30),
            LocalDate(2019, 1, 27),
            LocalDate(2019, 2, 24),
            LocalDate(2019, 3, 31),
            LocalDate(2019, 4, 14),
            LocalDate(2019, 4, 28),
            LocalDate(2019, 5, 26),
            LocalDate(2019, 6, 30),
            LocalDate(2019, 7, 28),
            LocalDate(2019, 8, 25),
            LocalDate(2019, 9, 29),
            LocalDate(2019, 10, 27),
            LocalDate(2019, 11, 24),
            LocalDate(2019, 12, 15),
            LocalDate(2019, 12, 22),
            LocalDate(2019, 12, 29),
            LocalDate(2020, 1, 26),
            LocalDate(2020, 4, 5),
            LocalDate(2020, 4, 26),
            LocalDate(2020, 6, 28),
            LocalDate(2020, 8, 30),
            LocalDate(2020, 12, 6),
            LocalDate(2020, 12, 13),
            LocalDate(2020, 12, 20),
            LocalDate(2021, 1, 31),
            LocalDate(2021, 3, 28),
            LocalDate(2021, 4, 25),
            LocalDate(2021, 6, 27),
            LocalDate(2021, 8, 29),
            LocalDate(2021, 12, 12),
            LocalDate(2021, 12, 19),
    )

    fun isShoppingAllowed(date: LocalDate = LocalDate.fromDateFields(Date())): Boolean {
        return if (date.dayOfWeek != DateTimeConstants.SUNDAY) true
        else businessDays.contains(date)
    }

    fun getNextShoppingSunday(afterDate: LocalDate = LocalDate.fromDateFields(Date())): LocalDate {
        return businessDays.first {
            it.isAfter(afterDate)
        }
    }

    fun getAllRemainingSundays(afterDate: LocalDate = LocalDate.fromDateFields(Date())): List<LocalDate> {
        return businessDays.filter {
            it.isAfter(afterDate)
        }
    }

    fun calculateJobTime(nextShoppingSunday: LocalDate, notificationDays: Int?, notificationTime: Long?): Long {
        return if (notificationDays != null && notificationTime != null) {
            val date = nextShoppingSunday.minusDays(notificationDays)
            val notificationTime1 = Date(notificationTime)
            val fromDateFields = LocalTime.fromDateFields(notificationTime1)
            val dateTime = DateTime(date.year, date.monthOfYear, date.dayOfMonth, fromDateFields.hourOfDay, fromDateFields.minuteOfHour)
            dateTime.toDateTime().millis - System.currentTimeMillis()
        } else {
            nextShoppingSunday.toDateTime(LocalTime(12, 0, 0)).millis - System.currentTimeMillis()
        }
    }
}