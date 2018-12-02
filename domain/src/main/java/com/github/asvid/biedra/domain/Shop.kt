package com.github.asvid.biedra.domain

data class Shop(
    val id: String,
    val address: Address,
    var distance: Double?,
    val location: Position,
    val openHours: OpenHours
)

fun shop(block: ShopBuilder.() -> Unit): Shop = ShopBuilder().apply(block).build()

@DslMarker
annotation class ShopDsl

@ShopDsl
class ShopBuilder {

  var id: String = ""
  var distance: Double? = null
  lateinit var address: Address
  lateinit var openHours: OpenHours
  lateinit var location: Position

  fun address(block: AddressBuilder.() -> Unit) = AddressBuilder().apply(block).build()
  fun openHours(block: OpenHoursBuilder.() -> Unit) = OpenHoursBuilder().apply(block).build()
  fun location(block: PositionBuilder.() -> Unit) = PositionBuilder().apply(block).build()

  fun build(): Shop = Shop(id, address, distance, location, openHours)
}
