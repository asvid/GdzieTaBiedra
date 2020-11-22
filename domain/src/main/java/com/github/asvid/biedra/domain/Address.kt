package com.github.asvid.biedra.domain

/**
 * Address data class for [Shop]
 *
 * @property cityName - name of city/town/village, all [Shop]s should have this
 * @property streetName - name of street, all [Shop]s should have this
 * @property streetNumber - street number, not all [Shop]s has one
 * @property postalCode - not used currently
 * */
data class Address(
    val streetName: String,
    val cityName: String,
    val streetNumber: String,
    val postalCode: String
) {

  /**
   * String representation of [Address] in form: <street name> <street number>, <postal code> <city name>
   * */
  override fun toString(): String {
    return "$streetName $streetNumber, $postalCode $cityName"
  }
}

/**
 * DSL method for creating [Address]
 * */
fun address(block: AddressBuilder.() -> Unit) = AddressBuilder().apply(block).build()

/**
 * DLS builder for [Address]
 *
 * @property cityName
 * @property streetName
 * @property streetNumber
 * @property postalCode
 * */
@ShopDsl
class AddressBuilder {
  var streetName = ""
  var cityName = ""
  var streetNumber = ""
  var postalCode = ""

  /**
   * Builds [Address] from builder fields: [streetName], [streetNumber], [cityName], [postalCode]
   *
   * @return [Address]
   * */
  fun build(): Address = Address(streetName, cityName, streetNumber, postalCode)
}
