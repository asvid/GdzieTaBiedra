package com.github.asvid.biedra.domain

import org.joda.time.LocalTime
import org.junit.jupiter.api.Test

class TimeRangeTest {

  @Test
  fun `when time is in range method should return true`() {
    val start = LocalTime(6, 0)
    val end = LocalTime(21, 0)

    val timeRange = TimeRange(start, end)

    val inRange = LocalTime(8, 0)
    val inRange2 = LocalTime(6, 0)
    val inRange3 = LocalTime(21, 0)
    val notInRange = LocalTime(3, 0)
    val notInRange2 = LocalTime(5, 59)
    val notInRange3 = LocalTime(21, 1)

    assert(timeRange.withinRange(inRange))
    assert(timeRange.withinRange(inRange2))
    assert(timeRange.withinRange(inRange3))
    assert(!timeRange.withinRange(notInRange))
    assert(!timeRange.withinRange(notInRange2))
    assert(!timeRange.withinRange(notInRange3))
  }
}