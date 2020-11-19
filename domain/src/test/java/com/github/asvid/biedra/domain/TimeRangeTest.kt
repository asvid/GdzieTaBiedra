package com.github.asvid.biedra.domain

import org.junit.Test
import java.time.LocalTime

class TimeRangeTest {

    @Test
    fun `when time is in range method should return true`() {
        val start = LocalTime.of(6, 0)
        val end = LocalTime.of(21, 0)

        val timeRange = TimeRange(start, end)

        val inRange = LocalTime.of(8, 0)
        val inRange2 = LocalTime.of(6, 0)
        val inRange3 = LocalTime.of(21, 0)
        val notInRange = LocalTime.of(3, 0)
        val notInRange2 = LocalTime.of(5, 59)
        val notInRange3 = LocalTime.of(21, 1)

        assert(timeRange.withinRange(inRange))
        assert(timeRange.withinRange(inRange2))
        assert(timeRange.withinRange(inRange3))
        assert(!timeRange.withinRange(notInRange))
        assert(!timeRange.withinRange(notInRange2))
        assert(!timeRange.withinRange(notInRange3))
    }
}