package com.github.asvid.biedra.domain

import org.joda.time.LocalDate
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

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
}