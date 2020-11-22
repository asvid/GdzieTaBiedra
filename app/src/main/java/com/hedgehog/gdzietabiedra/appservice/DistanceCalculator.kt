package com.hedgehog.gdzietabiedra.appservice

import com.github.asvid.biedra.domain.Location
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

/**
 *  [DistanceCalculator] contains methods to calculate distance between [Location]s
 *
 * */
class DistanceCalculator {

    /**
     * Calculates distance in meters in straight line between 2 [Location]s
     *
     * @param pointA first position
     * @param pointB second position
     *
     * @return [Double] distance in straight line between 2 [Location]s in meters.
     *
     * */

    fun calculateDistance(pointA: Location, pointB: Location): Double {
        val pk = (180f / Math.PI).toFloat()

        val a1 = pointA.lat / pk
        val a2 = pointA.lng / pk
        val b1 = pointB.lat / pk
        val b2 = pointB.lng / pk

        val t1 = cos(a1) * cos(a2) * cos(b1) * cos(b2)
        val t2 = cos(a1) * sin(a2) * cos(b1) * sin(
                b2)
        val t3 = sin(a1) * sin(b1)
        val tt = acos(t1 + t2 + t3)

        return 6366000 * tt
    }
}