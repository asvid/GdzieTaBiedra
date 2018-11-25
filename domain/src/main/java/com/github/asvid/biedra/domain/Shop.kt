package com.github.asvid.biedra.domain

data class Shop(
    val id: String,
    val address: String,
    var distance: Double?,
    val location: Position,
    val openHours: String
)

fun shop(block: ShopBuilder.() -> Unit): Shop = ShopBuilder().apply(block).build()

@DslMarker
annotation class ShopDsl

@ShopDsl
class ShopBuilder {

  var id: String = ""
  var address: String = ""
  var distance: Double? = null
  lateinit var location: Position
  var openHours: String = ""

  fun location(block: PositionBuilder.() -> Unit) = PositionBuilder().apply(block).build()

  fun build(): Shop = Shop(id, address, distance, location, openHours)
}