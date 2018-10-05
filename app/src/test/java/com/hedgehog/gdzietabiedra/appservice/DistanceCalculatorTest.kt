package com.hedgehog.gdzietabiedra.appservice

import com.hedgehog.gdzietabiedra.domain.Point
import org.junit.Test

class DistanceCalculatorTest {

  private val distanceCalculator = DistanceCalculator()

  @Test
  fun `calculated distance should be more-less correct`() {
    val pointA = Point(52.593465, 15.914055)
    val pointB = Point(52.565976, 15.951845)
    val distanceAB = 3980.0

    System.out.println("distance: ${distanceCalculator.calculateDistance(pointA, pointB)}")

    assert(
        Math.abs(distanceCalculator.calculateDistance(pointA, pointB) - distanceAB) < 20)
  }

}