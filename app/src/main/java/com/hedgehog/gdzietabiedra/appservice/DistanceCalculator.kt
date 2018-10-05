package com.hedgehog.gdzietabiedra.appservice

import com.hedgehog.gdzietabiedra.domain.Point

class DistanceCalculator {

  fun calculateDistance(pointA: Point, pointB: Point): Double {
    val pk = (180f / Math.PI).toFloat()

    val a1 = pointA.lat / pk
    val a2 = pointA.lng / pk
    val b1 = pointB.lat / pk
    val b2 = pointB.lng / pk

    val t1 = Math.cos(a1.toDouble()) * Math.cos(a2.toDouble()) * Math.cos(b1.toDouble()) * Math.cos(
        b2.toDouble())
    val t2 = Math.cos(a1.toDouble()) * Math.sin(a2.toDouble()) * Math.cos(b1.toDouble()) * Math.sin(
        b2.toDouble())
    val t3 = Math.sin(a1.toDouble()) * Math.sin(b1.toDouble())
    val tt = Math.acos(t1 + t2 + t3)

    return 6366000 * tt
  }

}