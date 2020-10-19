package com.hedgehog.gdzietabiedra.appservice

import com.github.asvid.biedra.domain.Position

/**
 *  [DistanceCalculator] contains methods to calculate distance between [Position]s
 *
 * */
class DistanceCalculator {

  /**
   * Calculates distance in meters in straight line between 2 [Position]s
   *
   * @param pointA first position
   * @param pointB second position
   *
   * @return [Double] distance in straight line between 2 [Position]s in meters.
   *
   * @sample [com.hedgehog.gdzietabiedra.appservice.DistanceCalculatorSamples.calculateDistance]
   *
   * */
  suspend fun calculateDistance(pointA: Position, pointB: Position): Double {
    val pk = (180f / Math.PI).toFloat()

    val a1 = pointA.lat / pk
    val a2 = pointA.lng / pk
    val b1 = pointB.lat / pk
    val b2 = pointB.lng / pk

    val t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2)
    val t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(
        b2)
    val t3 = Math.sin(a1) * Math.sin(b1)
    val tt = Math.acos(t1 + t2 + t3)

    return 6366000 * tt
  }
}