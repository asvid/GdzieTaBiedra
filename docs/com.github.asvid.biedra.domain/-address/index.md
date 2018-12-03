[com.github.asvid.biedra.domain](../index.md) / [Address](./index.md)

# Address

`data class Address` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/domain/src/main/java/com/github/asvid/biedra/domain/Address.kt#L11)

Address data class for [Shop](../-shop/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Address(streetName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, cityName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, streetNumber: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, postalCode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>Address data class for [Shop](../-shop/index.md) |

### Properties

| Name | Summary |
|---|---|
| [cityName](city-name.md) | `val cityName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<ul><li>name of city/town/village, all [Shop](../-shop/index.md)s should have this</li></ul> |
| [postalCode](postal-code.md) | `val postalCode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<ul><li>not used currently</li></ul> |
| [streetName](street-name.md) | `val streetName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<ul><li>name of street, all [Shop](../-shop/index.md)s should have this</li></ul> |
| [streetNumber](street-number.md) | `val streetNumber: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<ul><li>street number, not all [Shop](../-shop/index.md)s has one</li></ul> |

### Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>String representation of [Address](./index.md) in form:  ,   |
