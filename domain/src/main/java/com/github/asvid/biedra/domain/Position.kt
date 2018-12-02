package com.github.asvid.biedra.domain

/**
 * Domain object for [Shop] position
 *
 * @property lat latitude
 * @property lng longitude
 * */
data class Position(
    val lat: Double,
    val lng: Double
)

/**
 * DLS method for [Position]
 * */
fun position(block: PositionBuilder.() -> Unit): Position = PositionBuilder().apply(block).build()

/**
 * DLS builder for [Position]
 *
 * @property lat
 * @property lng
 * */
@ShopDsl
class PositionBuilder {
  var lat: Double = 0.0
  var lng: Double = 0.0

  fun build(): Position = Position(lat, lng)
}
