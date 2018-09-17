package com.hedgehog.gdzietabiedra.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Int.toLocalNumberFormat(locale: Locale): String {
  return NumberFormat.getNumberInstance(locale).format(this)
}

fun String.toDate(format: String): Date {
  val formatter = SimpleDateFormat(format)
  return formatter.parse(this)
}

fun Date.print(format: String): String {
  val dateFormat = SimpleDateFormat(format)
  return dateFormat.format(this)
}
