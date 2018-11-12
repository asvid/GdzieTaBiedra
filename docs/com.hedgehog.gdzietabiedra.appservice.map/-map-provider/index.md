[com.hedgehog.gdzietabiedra.appservice.map](../index.md) / [MapProvider](./index.md)

# MapProvider

`interface MapProvider` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/appservice/map/MapProvider.kt#L9)

### Functions

| Name | Summary |
|---|---|
| [clearMap](clear-map.md) | `abstract fun clearMap(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [drawMarker](draw-marker.md) | `abstract fun drawMarker(point: `[`ShopMarker`](../-shop-marker/index.md)`, showInfo: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [drawMarkers](draw-markers.md) | `abstract fun drawMarkers(points: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`ShopMarker`](../-shop-marker/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [goToPosition](go-to-position.md) | `abstract fun goToPosition(position: `[`Position`](../../com.github.asvid.biedra.domain/-position/index.md)`, mapZoom: `[`MapZoom`](../-map-zoom/index.md)` = MEDIUM): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [mapClicked](map-clicked.md) | `abstract fun mapClicked(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |
| [mapMoved](map-moved.md) | `abstract fun mapMoved(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Position`](../../com.github.asvid.biedra.domain/-position/index.md)`>` |
| [readMapPosition](read-map-position.md) | `abstract fun readMapPosition(): `[`Position`](../../com.github.asvid.biedra.domain/-position/index.md) |
| [selectShop](select-shop.md) | `abstract fun selectShop(shop: `[`Shop`](../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`): `[`Completable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Completable.html) |
| [shopMarkerClicked](shop-marker-clicked.md) | `abstract fun shopMarkerClicked(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`ShopMarker`](../-shop-marker/index.md)`>` |

### Inheritors

| Name | Summary |
|---|---|
| [GoogleMapProvider](../-google-map-provider/index.md) | `class GoogleMapProvider : `[`MapProvider`](./index.md) |
