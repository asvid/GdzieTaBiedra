package com.github.asvid.biedra.domain

data class Address(
    val streetName: String,
    val cityName: String,
    val streetNumber: String,
    val postalCode: String
)

fun address(block: AddressBuilder.() -> Unit) = AddressBuilder().apply(block).build()

@ShopDsl
class AddressBuilder {
  var streetName = ""
  var cityName = ""
  var streetNumber = ""
  var postalCode = ""

  fun build(): Address = Address(streetName, cityName, streetNumber, postalCode)
}