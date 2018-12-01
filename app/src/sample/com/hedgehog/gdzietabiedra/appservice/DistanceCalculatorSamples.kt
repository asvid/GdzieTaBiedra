package com.hedgehog.gdzietabiedra.appservice

import com.github.asvid.biedra.domain.Position

/**
 * @suppress
 * */
class DistanceCalculatorSamples {

  fun calculateDistance() {
    val positionA = Position(52.6546, 58.3541)
    val positionB = Position(58.46, 57.35)

    val distanceInMeters = DistanceCalculator().calculateDistance(positionA, positionB)
  }
}