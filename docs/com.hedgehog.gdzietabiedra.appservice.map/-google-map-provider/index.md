[com.hedgehog.gdzietabiedra.appservice.map](../index.md) / [GoogleMapProvider](./index.md)

# GoogleMapProvider

`class GoogleMapProvider : `[`MapProvider`](../-map-provider/index.md)

### Functions

| Name | Summary |
|---|---|
| [clearMap](clear-map.md) | `fun clearMap(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [drawMarker](draw-marker.md) | `fun drawMarker(shopMarker: `[`ShopMarker`](../-shop-marker/index.md)`, showInfo: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [drawMarkers](draw-markers.md) | `fun drawMarkers(points: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`ShopMarker`](../-shop-marker/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [goToPosition](go-to-position.md) | `fun goToPosition(position: `[`Position`](file:/home/adam/repo/GdzieTaBiedra/docs/domain/com.github.asvid.biedra.domain/-position/index.md)`, mapZoom: `[`MapZoom`](../-map-zoom/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [mapClicked](map-clicked.md) | `fun mapClicked(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |
| [mapMoved](map-moved.md) | `fun mapMoved(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Position`](file:/home/adam/repo/GdzieTaBiedra/docs/domain/com.github.asvid.biedra.domain/-position/index.md)`>` |
| [readMapPosition](read-map-position.md) | `fun readMapPosition(): `[`Position`](file:/home/adam/repo/GdzieTaBiedra/docs/domain/com.github.asvid.biedra.domain/-position/index.md) |
| [selectShop](select-shop.md) | `fun selectShop(shop: `[`Shop`](file:/home/adam/repo/GdzieTaBiedra/docs/domain/com.hedgehog.gdzietabiedra.domain/-shop/index.md)`): `[`Completable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Completable.html) |
| [shopMarkerClicked](shop-marker-clicked.md) | `fun shopMarkerClicked(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`ShopMarker`](../-shop-marker/index.md)`>` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [create](create.md) | `fun create(googleMap: GoogleMap, context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`GoogleMapProvider`](./index.md) |
