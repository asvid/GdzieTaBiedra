package com.github.asvid.biedra.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class SundayShoppingTest {

    @ParameterizedTest
    @CsvSource(
            "2018, 12, 3, true",
            "2018, 12, 23, true",
            "2018, 12, 9, false",
    )
    fun `should return true if shop is open on provided date`(
            year: Int,
            month: Int,
            day: Int,
            isShopOpen: Boolean
    ) {
        val testedDay = LocalDate.of(year, month, day)

        assert(SundayShopping.isShoppingAllowed(testedDay) == isShopOpen)
    }

    @ParameterizedTest
    @CsvSource(
            "2018, 1, 1, 2018, 12, 2",
            "2019, 12, 10, 2019, 12, 15",
            "2019, 12, 15, 2019, 12, 22",
            "2021, 12, 15, 2021, 12, 19",
    )
    fun `should return expected next shopping sunday after selected day`(
            year: Int,
            month: Int,
            day: Int,
            nextYear: Int,
            nextMonth: Int,
            nextDay: Int,
    ) {
        val testedDay = LocalDate.of(year, month, day)
        val nextShoppingSunday = LocalDate.of(nextYear, nextMonth, nextDay)

        assert(SundayShopping.getNextShoppingSunday(testedDay) == nextShoppingSunday)
    }

    @Test
    fun `should return all shopping sundays after selected day`() {
        val afterDate = LocalDate.of(2020, 12, 30)
        val allRemainingSundays = SundayShopping.getAllRemainingSundays(afterDate)
        assert(allRemainingSundays.size == 7)
    }

    @Test
    fun `should calculate notification date properly`() {
        val initDate = LocalDate.of(2020, 12, 15)
        val initTime = LocalTime.of(12, 30)
        val calculatedDateTime = SundayShopping.calculateJobTime(initDate, 10, initTime) + System.currentTimeMillis()
        val date = Instant.ofEpochMilli(calculatedDateTime).atZone(ZoneId.systemDefault()).toLocalDateTime()
        println(date)

        assert(date.hour == 12)
        assert(date.minute == 30)
        assert(date.dayOfMonth == 5)
        assert(date.monthValue == 12)
        assert(date.year == 2020)
    }

    @Test
    fun `dummy`() {
        val formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale("pl"))
        println(LocalTime.of(5, 2).format(formatter))
        println(LocalTime.of(15, 2).format(formatter))
    }
}