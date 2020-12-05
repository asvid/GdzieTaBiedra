package com.github.asvid.biedra.domain

import com.github.asvid.biedra.domain.shops.toOpenHours
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class OpenHoursKtTest {

    @ParameterizedTest
    @CsvSource(
            "06.30 - 21.00, 6, 30, 21, 0",
            "06.30 - 22.00, 6, 30, 22, 0",
            "08.00 - 20.00, 8, 0, 20, 0",
            "08.00-20.00, 8, 0, 20, 0",
            "08:00-20:00, 8, 0, 20, 0",
            "08:00 - 20:00, 8, 0, 20, 0",
    )
    fun `strings should be transformed into pair of dates correctly`(
            openingHoursString: String,
            startHour: Int,
            startMinutes: Int,
            endHour: Int,
            endMinutes: Int
    ) {
        val openingHours = openingHoursString.toOpenHours()!!

        assertEquals(startHour, openingHours.start.hourOfDay)
        assertEquals(startMinutes, openingHours.start.minuteOfHour)
        assertEquals(endHour, openingHours.end.hourOfDay)
        assertEquals(endMinutes, openingHours.end.minuteOfHour)
    }

    @Test
    fun `incorrect string should return null when transformed to Open Hours`(){
        val incorrectString = "zamkniete"

        assertNull(incorrectString.toOpenHours())
    }
}