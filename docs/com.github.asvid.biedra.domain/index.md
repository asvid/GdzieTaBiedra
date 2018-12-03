[com.github.asvid.biedra.domain](./index.md)

## Package com.github.asvid.biedra.domain

### Types

| Name | Summary |
|---|---|
| [Address](-address/index.md) | `data class Address`<br>Address data class for [Shop](-shop/index.md) |
| [AddressBuilder](-address-builder/index.md) | `class AddressBuilder`<br>DLS builder for [Address](-address/index.md) |
| [OpenHours](-open-hours/index.md) | `data class OpenHours`<br>[Shop](-shop/index.md) open hours, contains [TimeRange](-time-range/index.md) for weekdays, saturday and sunday |
| [OpenHoursBuilder](-open-hours-builder/index.md) | `class OpenHoursBuilder`<br>DSL builder for [OpenHours](-open-hours/index.md) |
| [Position](-position/index.md) | `data class Position`<br>Domain object for [Shop](-shop/index.md) position |
| [PositionBuilder](-position-builder/index.md) | `class PositionBuilder`<br>DLS builder for [Position](-position/index.md) |
| [Shop](-shop/index.md) | `data class Shop`<br>Biedronka domain object |
| [ShopBuilder](-shop-builder/index.md) | `class ShopBuilder`<br>DSL Builder for [Shop](-shop/index.md) |
| [SundayShopping](-sunday-shopping/index.md) | `object SundayShopping` |
| [TimeRange](-time-range/index.md) | `class TimeRange`<br>[TimeRange](-time-range/index.md) allows easer storing and manipulating time ranges, for example: [Shop](-shop/index.md) open hours This class uses [Duration](#) and [LocalTime](#) from JodaTime library, because similar Java classes are available from API26 |

### Annotations

| Name | Summary |
|---|---|
| [ShopDsl](-shop-dsl/index.md) | `annotation class ShopDsl` |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [kotlin.String](kotlin.-string/index.md) |  |

### Functions

| Name | Summary |
|---|---|
| [address](address.md) | `fun address(block: `[`AddressBuilder`](-address-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Address`](-address/index.md)<br>DSL method for creating [Address](-address/index.md) |
| [getForToday](get-for-today.md) | `fun `[`OpenHours`](-open-hours/index.md)`.getForToday(): `[`TimeRange`](-time-range/index.md) |
| [openHours](open-hours.md) | `fun openHours(block: `[`OpenHoursBuilder`](-open-hours-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`OpenHours`](-open-hours/index.md)<br>DLS method for [OpenHours](-open-hours/index.md) |
| [position](position.md) | `fun position(block: `[`PositionBuilder`](-position-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Position`](-position/index.md)<br>DLS method for [Position](-position/index.md) |
| [shop](shop.md) | `fun shop(block: `[`ShopBuilder`](-shop-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Shop`](-shop/index.md)<br>DSL method for creating [Shop](-shop/index.md) |
