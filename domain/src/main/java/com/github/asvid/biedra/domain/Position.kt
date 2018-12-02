package com.github.asvid.biedra.domain

data class Position(
    val lat: Double,
    val lng: Double
)

fun position(block: PositionBuilder.() -> Unit): Position = PositionBuilder().apply(block).build()

@ShopDsl
class PositionBuilder {
  var lat: Double = 0.0
  var lng: Double = 0.0

  fun build(): Position = Position(lat, lng)
}
