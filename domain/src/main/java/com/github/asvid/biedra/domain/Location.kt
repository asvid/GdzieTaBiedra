package com.github.asvid.biedra.domain

/**
 * Domain object for [Shop] position
 *
 * @property lat latitude
 * @property lng longitude
 * */
data class Location(
    val lat: Double,
    val lng: Double
)

/**
 * DLS method for [Location]
 * */
fun position(block: PositionBuilder.() -> Unit): Location = PositionBuilder().apply(block).build()

/**
 * DLS builder for [Location]
 *
 * @property lat
 * @property lng
 * */
@ShopDsl
class PositionBuilder {
  var lat: Double = 0.0
  var lng: Double = 0.0

  fun build(): Location = Location(lat, lng)
}
