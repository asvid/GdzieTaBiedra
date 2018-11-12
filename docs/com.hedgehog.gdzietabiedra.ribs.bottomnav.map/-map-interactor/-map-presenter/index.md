[com.hedgehog.gdzietabiedra.ribs.bottomnav.map](../../index.md) / [MapInteractor](../index.md) / [MapPresenter](./index.md)

# MapPresenter

`interface MapPresenter` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/bottomnav/map/MapInteractor.kt#L167)

Presenter interface implemented by this RIB's view.

### Functions

| Name | Summary |
|---|---|
| [initView](init-view.md) | `abstract fun initView(): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`MapProvider`](../../../com.hedgehog.gdzietabiedra.appservice.map/-map-provider/index.md)`>` |
| [navigationButtonListener](navigation-button-listener.md) | `abstract fun navigationButtonListener(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>` |
| [startNavigation](start-navigation.md) | `abstract fun startNavigation(shop: `[`Shop`](../../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [switchNavigationButton](switch-navigation-button.md) | `abstract fun switchNavigationButton(visible: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [MapView](../../-map-view/index.md) | `class MapView : `[`FrameLayout`](https://developer.android.com/reference/android/widget/FrameLayout.html)`, `[`MapPresenter`](./index.md)<br>Top level view for {@link MapBuilder.MapScope}. |
