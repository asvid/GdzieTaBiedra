package com.github.asvid.biedra.domain

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
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
        val testedDay = LocalDate(year, month, day)

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
        val testedDay = LocalDate(year, month, day)
        val nextShoppingSunday = LocalDate(nextYear, nextMonth, nextDay)

        assert(SundayShopping.getNextShoppingSunday(testedDay) == nextShoppingSunday)
    }

    @Test
    fun `should return all shopping sundays after selected day`(){
        val afterDate = LocalDate(2020, 12, 30)
        val allRemainingSundays = SundayShopping.getAllRemainingSundays(afterDate)
        assert(allRemainingSundays.size == 7)
    }

    @Test
    fun `should calculate notification date properly`(){
        val initDate = LocalDate(2020, 12, 15)
        val initTime = Date().apply {
            hours = 12
            minutes = 30
        }
        val calculatedDateTime = SundayShopping.calculateJobTime(initDate, 10, initTime.time) + System.currentTimeMillis()
        val date = DateTime(calculatedDateTime)
        println(date)

        assert(date.hourOfDay == 12)
        assert(date.minuteOfHour == 30)
        assert(date.dayOfMonth == 5)
        assert(date.monthOfYear == 12)
        assert(date.year == 2020)
    }
}