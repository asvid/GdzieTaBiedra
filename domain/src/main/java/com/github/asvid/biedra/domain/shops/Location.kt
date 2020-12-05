package com.github.asvid.biedra.domain.shops

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
fun location(block: LocationBuilder.() -> Unit): Location = LocationBuilder().apply(block).build()

/**
 * DLS builder for [Location]
 *
 * @property lat
 * @property lng
 * */
@ShopDsl
class LocationBuilder {
  var lat: Double = 0.0
  var lng: Double = 0.0

  fun build(): Location = Location(lat, lng)
}
