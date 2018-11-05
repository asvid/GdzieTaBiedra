[com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist](../index.md) / [ShopsListInteractor](./index.md)

# ShopsListInteractor

`class ShopsListInteractor : `[`BaseInteractor`](../../com.uber.rib.core/-base-interactor/index.md)`<`[`ShopsListPresenter`](-shops-list-presenter/index.md)`, `[`ShopsListRouter`](../-shops-list-router/index.md)`>`

### Types

| Name | Summary |
|---|---|
| [ShopsListPresenter](-shops-list-presenter/index.md) | `interface ShopsListPresenter` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ShopsListInteractor()` |

### Properties

| Name | Summary |
|---|---|
| [analytics](analytics.md) | `lateinit var analytics: `[`Analytics`](../../com.hedgehog.gdzietabiedra.utils.analytics/-analytics/index.md) |
| [listener](listener.md) | `lateinit var listener: `[`ShopListListener`](../-shop-list-listener/index.md) |
| [locationWatchdog](location-watchdog.md) | `lateinit var locationWatchdog: `[`LocationWatchdog`](../../com.hedgehog.gdzietabiedra.appservice/-location-watchdog/index.md) |
| [presenter](presenter.md) | `lateinit var presenter: `[`ShopsListPresenter`](-shops-list-presenter/index.md) |
| [shopsService](shops-service.md) | `lateinit var shopsService: `[`ShopService`](../../com.hedgehog.gdzietabiedra.appservice/-shop-service/index.md) |

### Inherited Properties

| Name | Summary |
|---|---|
| [modelKey](../../com.uber.rib.core/-base-interactor/model-key.md) | `val modelKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [didBecomeActive](did-become-active.md) | `fun didBecomeActive(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getRibName](get-rib-name.md) | `fun getRibName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [addDisposable](../../com.uber.rib.core/-base-interactor/add-disposable.md) | `fun addDisposable(disposable: `[`Disposable`](http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/Disposable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [addToDisposables](../../com.uber.rib.core/-base-interactor/add-to-disposables.md) | `fun `[`Disposable`](http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/Disposable.html)`.addToDisposables(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onSaveInstanceState](../../com.uber.rib.core/-base-interactor/on-save-instance-state.md) | `open fun onSaveInstanceState(outState: Bundle): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [restoreRouter](../../com.uber.rib.core/-base-interactor/restore-router.md) | `open fun <T : Router<out Interactor<*, *>, out InteractorBaseComponent<*>>> restoreRouter(clazz: `[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<`[`T`](../../com.uber.rib.core/-base-interactor/restore-router.md#T)`>, childInfo: `[`Serializable`](https://developer.android.com/reference/java/io/Serializable.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [willResignActive](../../com.uber.rib.core/-base-interactor/will-resign-active.md) | `open fun willResignActive(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
