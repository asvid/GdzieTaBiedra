package com.github.asvid.biedra.domain

import org.joda.time.LocalDate
import org.junit.Test

class SundayShoppingTest {

  @Test
  fun `should return true if shopping is allowed for provided date`() {

    val notSunday = LocalDate(2018, 12, 3)
    val shoppingSunday = LocalDate(2018, 12, 23)
    val noShoppingSunday = LocalDate(2018, 12, 9)

    assert(SundayShopping.isShoppingAllowed(notSunday))
    assert(SundayShopping.isShoppingAllowed(shoppingSunday))
    assert(!SundayShopping.isShoppingAllowed(noShoppingSunday))
  }
}