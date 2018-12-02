package com.github.asvid.biedra.domain

import org.joda.time.DateTimeConstants
import org.joda.time.LocalDate

object SundayShopping {
  private val shoppingSundays = listOf(
      LocalDate(2018, 12, 2),
      LocalDate(2018, 12, 16),
      LocalDate(2018, 12, 23),
      LocalDate(2018, 12, 30),
      LocalDate(2019, 1, 27),
      LocalDate(2019, 2, 24),
      LocalDate(2019, 3, 31),
      LocalDate(2019, 4, 14),
      LocalDate(2019, 4, 28),
      LocalDate(2019, 5, 26),
      LocalDate(2019, 6, 30),
      LocalDate(2019, 7, 28),
      LocalDate(2019, 8, 25),
      LocalDate(2019, 9, 29),
      LocalDate(2019, 10, 27),
      LocalDate(2019, 11, 24),
      LocalDate(2019, 12, 15),
      LocalDate(2019, 12, 22),
      LocalDate(2019, 12, 29),
      LocalDate(2020, 1, 26),
      LocalDate(2020, 4, 5),
      LocalDate(2020, 4, 26),
      LocalDate(2020, 6, 28),
      LocalDate(2020, 8, 30),
      LocalDate(2020, 12, 13),
      LocalDate(2020, 12, 20)
  )

  fun isShoppingAllowed(date: LocalDate = LocalDate()): Boolean {
    return if (date.dayOfWeek != DateTimeConstants.SUNDAY) true
    else shoppingSundays.contains(date)
  }
}