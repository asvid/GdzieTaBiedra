package com.github.asvid.biedra.domain

import org.junit.Assert
import org.junit.Test

class OpenHoursKtTest {

  @Test
  fun `strings should be transformed into pair of dates correctly`() {

// Given some string hour ranges
      val openingHours1 = "06.30 - 21.00"
      val openingHours2 = "06.30 - 22.00"
      val openingHours3 = "08.00 - 20.00"

//    When transforming them to Pair of Dates
      val openPair1 = openingHours1.toOpenHours()!!
      val openPair2 = openingHours2.toOpenHours()!!
      val openPair3 = openingHours3.toOpenHours()!!

//    Dates should be correct
      Assert.assertEquals(6, openPair1.start.hour)
      Assert.assertEquals(30, openPair1.start.minute)
      Assert.assertEquals(6, openPair2.start.hour)
      Assert.assertEquals(30, openPair2.start.minute)
      Assert.assertEquals(8, openPair3.start.hour)
      Assert.assertEquals(0, openPair3.start.minute)

      Assert.assertEquals(21, openPair1.end.hour)
      Assert.assertEquals(0, openPair1.end.minute)
      Assert.assertEquals(22, openPair2.end.hour)
      Assert.assertEquals(0, openPair2.end.minute)
      Assert.assertEquals(20, openPair3.end.hour)
      Assert.assertEquals(0, openPair3.end.minute)
  }
}