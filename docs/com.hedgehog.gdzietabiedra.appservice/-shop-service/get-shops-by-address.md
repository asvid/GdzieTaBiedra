[com.hedgehog.gdzietabiedra.appservice](../index.md) / [ShopService](index.md) / [getShopsByAddress](./get-shops-by-address.md)

# getShopsByAddress

`fun getShopsByAddress(address: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, location: `[`Position`](../../com.github.asvid.biedra.domain/-position/index.md)`?): `[`Flowable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Flowable.html)`<`[`Shop`](../../com.github.asvid.biedra.domain/-shop/index.md)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/appservice/ShopService.kt#L27)

### Parameters

`address` -
* user query for city or street where [Shop](../../com.github.asvid.biedra.domain/-shop/index.md) might be

`location` -
* user location to calculate distance to returned [Shop](../../com.github.asvid.biedra.domain/-shop/index.md)

**Return**
[Flowable](http://reactivex.io/RxJava/javadoc/io/reactivex/Flowable.html) of [Shop](../../com.github.asvid.biedra.domain/-shop/index.md)s that fit to query

