[com.github.asvid.biedra.domain](../index.md) / [ShopBuilder](./index.md)

# ShopBuilder

`class ShopBuilder` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/domain/src/main/java/com/github/asvid/biedra/domain/Shop.kt#L38)

DSL Builder for [Shop](../-shop/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ShopBuilder()`<br>DSL Builder for [Shop](../-shop/index.md) |

### Properties

| Name | Summary |
|---|---|
| [address](address.md) | `lateinit var address: `[`Address`](../-address/index.md)<br>uses [AddressBuilder](../-address-builder/index.md) |
| [distance](distance.md) | `var distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`?` |
| [id](id.md) | `var id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [location](location.md) | `lateinit var location: `[`Position`](../-position/index.md)<br>uses [ProcessBuilder](https://developer.android.com/reference/java/lang/ProcessBuilder.html) |
| [openHours](open-hours.md) | `lateinit var openHours: `[`OpenHours`](../-open-hours/index.md)<br>uses [OpenHoursBuilder](../-open-hours-builder/index.md) |

### Functions

| Name | Summary |
|---|---|
| [address](address.md) | `fun address(block: `[`AddressBuilder`](../-address-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Address`](../-address/index.md) |
| [build](build.md) | `fun build(): `[`Shop`](../-shop/index.md) |
| [location](location.md) | `fun location(block: `[`PositionBuilder`](../-position-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Position`](../-position/index.md) |
| [openHours](open-hours.md) | `fun openHours(block: `[`OpenHoursBuilder`](../-open-hours-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`OpenHours`](../-open-hours/index.md) |
