package com.github.asvid.biedra.domain

/**
 * Biedronka domain object
 *
 * @property id ID of shop in Biedronka DB
 * @property address of shop, often without street number and postal code
 * @property distance current user distance to shop, its calculated ad hoc
 * @property location lat lng of shop
 * @property openHours opening hours for weekday, saturday and sunday
 * */
data class Shop(
    val id: String,
    val address: Address,
    var distance: Double?,
    val location: Position,
    val openHours: OpenHours
)

/**
 * DSL method for creating [Shop]
 * */
fun shop(block: ShopBuilder.() -> Unit): Shop = ShopBuilder().apply(block).build()

@DslMarker
annotation class ShopDsl

/**
 * DSL Builder for [Shop]
 *
 * @property id
 * @property address uses [AddressBuilder]
 * @property distance
 * @property location uses [ProcessBuilder]
 * @property openHours uses [OpenHoursBuilder]
 * */
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
