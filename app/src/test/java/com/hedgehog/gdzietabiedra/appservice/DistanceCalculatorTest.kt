package com.hedgehog.gdzietabiedra.appservice

import com.github.asvid.biedra.domain.Location
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.math.abs

class DistanceCalculatorTest {

    private val distanceCalculator = DistanceCalculator()

    @Test
    fun `calculated distance should be more-less correct`() = runBlocking {
        val pointA = Location(52.593465, 15.914055)
        val pointB = Location(52.565976, 15.951845)
        val distanceAB = 3980.0

        println("distance: ${distanceCalculator.calculateDistance(pointA, pointB)}")

        assert(abs(distanceCalculator.calculateDistance(pointA, pointB) - distanceAB) < 20)
    }
}