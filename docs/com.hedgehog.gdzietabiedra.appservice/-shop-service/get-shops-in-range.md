[com.hedgehog.gdzietabiedra.appservice](../index.md) / [ShopService](index.md) / [getShopsInRange](./get-shops-in-range.md)

# getShopsInRange

`fun getShopsInRange(location: `[`Position`](../../com.github.asvid.biedra.domain/-position/index.md)`?, range: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Flowable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Flowable.html)`<`[`Shop`](../../com.github.asvid.biedra.domain/-shop/index.md)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/appservice/ShopService.kt#L39)

### Parameters

`location` -
* user location to narrow DB query

`range` -
* range in which shops around [location](get-shops-in-range.md#com.hedgehog.gdzietabiedra.appservice.ShopService$getShopsInRange(com.github.asvid.biedra.domain.Position, kotlin.Double)/location) will be returned

**Return**
[Flowable](http://reactivex.io/RxJava/javadoc/io/reactivex/Flowable.html) of [Shop](../../com.github.asvid.biedra.domain/-shop/index.md)s that are in [range](get-shops-in-range.md#com.hedgehog.gdzietabiedra.appservice.ShopService$getShopsInRange(com.github.asvid.biedra.domain.Position, kotlin.Double)/range) of [location](get-shops-in-range.md#com.hedgehog.gdzietabiedra.appservice.ShopService$getShopsInRange(com.github.asvid.biedra.domain.Position, kotlin.Double)/location)

