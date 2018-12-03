[com.github.asvid.biedra.domain](../index.md) / [Shop](./index.md)

# Shop

`data class Shop` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/domain/src/main/java/com/github/asvid/biedra/domain/Shop.kt#L12)

Biedronka domain object

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Shop(id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, address: `[`Address`](../-address/index.md)`, distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`?, location: `[`Position`](../-position/index.md)`, openHours: `[`OpenHours`](../-open-hours/index.md)`)`<br>Biedronka domain object |

### Properties

| Name | Summary |
|---|---|
| [address](address.md) | `val address: `[`Address`](../-address/index.md)<br>of shop, often without street number and postal code |
| [distance](distance.md) | `var distance: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`?`<br>current user distance to shop, its calculated ad hoc |
| [id](id.md) | `val id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ID of shop in Biedronka DB |
| [location](location.md) | `val location: `[`Position`](../-position/index.md)<br>lat lng of shop |
| [openHours](open-hours.md) | `val openHours: `[`OpenHours`](../-open-hours/index.md)<br>opening hours for weekday, saturday and sunday |
