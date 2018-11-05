[com.hedgehog.gdzietabiedra.ribs.bottomnav.map](../index.md) / [MapView](./index.md)

# MapView

`class MapView : `[`FrameLayout`](https://developer.android.com/reference/android/widget/FrameLayout.html)`, `[`MapPresenter`](../-map-interactor/-map-presenter/index.md)

Top level view for {@link MapBuilder.MapScope}.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MapView(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`? = null, defStyle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0)`<br>Top level view for {@link MapBuilder.MapScope}. |

### Functions

| Name | Summary |
|---|---|
| [initView](init-view.md) | `fun initView(): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`MapProvider`](../../com.hedgehog.gdzietabiedra.appservice.map/-map-provider/index.md)`>` |
| [navigationButtonListener](navigation-button-listener.md) | `fun navigationButtonListener(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`>` |
| [startNavigation](start-navigation.md) | `fun startNavigation(shop: `[`Shop`](file:/home/adam/repo/GdzieTaBiedra/docs/domain/com.hedgehog.gdzietabiedra.domain/-shop/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [switchNavigationButton](switch-navigation-button.md) | `fun switchNavigationButton(visible: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
