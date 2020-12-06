package com.github.asvid.biedra.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

object SundayShopping {
    val businessDays = listOf(
            LocalDate.of(2018, 12, 2),
            LocalDate.of(2018, 12, 16),
            LocalDate.of(2018, 12, 23),
            LocalDate.of(2018, 12, 30),
            LocalDate.of(2019, 1, 27),
            LocalDate.of(2019, 2, 24),
            LocalDate.of(2019, 3, 31),
            LocalDate.of(2019, 4, 14),
            LocalDate.of(2019, 4, 28),
            LocalDate.of(2019, 5, 26),
            LocalDate.of(2019, 6, 30),
            LocalDate.of(2019, 7, 28),
            LocalDate.of(2019, 8, 25),
            LocalDate.of(2019, 9, 29),
            LocalDate.of(2019, 10, 27),
            LocalDate.of(2019, 11, 24),
            LocalDate.of(2019, 12, 15),
            LocalDate.of(2019, 12, 22),
            LocalDate.of(2019, 12, 29),
            LocalDate.of(2020, 1, 26),
            LocalDate.of(2020, 4, 5),
            LocalDate.of(2020, 4, 26),
            LocalDate.of(2020, 6, 28),
            LocalDate.of(2020, 8, 30),
            LocalDate.of(2020, 12, 6),
            LocalDate.of(2020, 12, 13),
            LocalDate.of(2020, 12, 20),
            LocalDate.of(2021, 1, 31),
            LocalDate.of(2021, 3, 28),
            LocalDate.of(2021, 4, 25),
            LocalDate.of(2021, 6, 27),
            LocalDate.of(2021, 8, 29),
            LocalDate.of(2021, 12, 12),
            LocalDate.of(2021, 12, 19),
    )

    fun isShoppingAllowed(date: LocalDate = LocalDate.now()): Boolean {
        return if (date.dayOfWeek != DayOfWeek.SUNDAY) true
        else businessDays.contains(date)
    }

    fun getNextShoppingSunday(afterDate: LocalDate = LocalDate.now()): LocalDate {
        return businessDays.first {
            it.isAfter(afterDate)
        }
    }

    fun getAllRemainingSundays(afterDate: LocalDate = LocalDate.now()): List<LocalDate> {
        return businessDays.filter {
            it.isAfter(afterDate)
        }
    }

    fun calculateJobTime(nextShoppingSunday: LocalDate, notificationDays: Int, notificationTime: java.time.LocalTime): Long {
        val date = nextShoppingSunday.minusDays(notificationDays.toLong())
        val dateTime = LocalDateTime.of(date.year, date.monthValue, date.dayOfMonth, notificationTime.hour, notificationTime.minute)
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - System.currentTimeMillis()
    }
}