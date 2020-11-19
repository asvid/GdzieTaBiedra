package com.github.asvid.biedra.domain

import org.junit.Test
import java.time.LocalDate

class SundayShoppingTest {

  @Test
  fun `should return true if shopping is allowed for provided date`() {

    val notSunday = LocalDate.of(2018, 12, 3)
    val shoppingSunday = LocalDate.of(2018, 12, 23)
    val noShoppingSunday = LocalDate.of(2018, 12, 9)

    assert(SundayShopping.isShoppingAllowed(notSunday))
    assert(SundayShopping.isShoppingAllowed(shoppingSunday))
    assert(!SundayShopping.isShoppingAllowed(noShoppingSunday))
  }
}