[com.hedgehog.gdzietabiedra.appservice](../index.md) / [ShopService](./index.md)

# ShopService

`class ShopService` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/appservice/ShopService.kt#L12)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ShopService(shopsRepository: `[`ShopsRepository`](../../com.hedgehog.gdzietabiedra.data.repository.shops/-shops-repository/index.md)`, distanceCalculator: `[`DistanceCalculator`](../-distance-calculator/index.md)`)` |

### Functions

| Name | Summary |
|---|---|
| [getShopsByAddress](get-shops-by-address.md) | `fun getShopsByAddress(address: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, location: `[`Position`](../../com.github.asvid.biedra.domain/-position/index.md)`?): `[`Flowable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Flowable.html)`<`[`Shop`](../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`>` |
| [getShopsInRange](get-shops-in-range.md) | `fun getShopsInRange(location: `[`Position`](../../com.github.asvid.biedra.domain/-position/index.md)`?, range: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Flowable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Flowable.html)`<`[`Shop`](../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`>` |
