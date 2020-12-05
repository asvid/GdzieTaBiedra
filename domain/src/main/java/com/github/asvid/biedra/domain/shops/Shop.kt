package com.github.asvid.biedra.domain.shops

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
        val name: String?,
        var distance: Double?,
        val location: Location,
        val openHours: OpenHours,
        val features: Set<ShopFeature>
)

fun Shop.printName() = this.name ?: this.address.toString()

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
 * @property features uses [FeaturesBuilder]
 * */
@ShopDsl
class ShopBuilder {
    var id: String = ""
    var name: String? = null
    var distance: Double? = null
    lateinit var address: Address
    lateinit var openHours: OpenHours
    lateinit var location: Location
    lateinit var features: Set<ShopFeature>

    fun address(block: AddressBuilder.() -> Unit) = AddressBuilder().apply(block).build()
    fun openHours(block: OpenHoursBuilder.() -> Unit) = OpenHoursBuilder().apply(block).build()
    fun location(block: LocationBuilder.() -> Unit) = LocationBuilder().apply(block).build()
    fun features(block: FeaturesBuilder.() -> Unit) = FeaturesBuilder().apply(block).build()

    fun build(): Shop = Shop(id, address, name, distance, location, openHours, features)
}
