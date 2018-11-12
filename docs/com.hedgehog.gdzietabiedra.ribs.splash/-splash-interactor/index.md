[com.hedgehog.gdzietabiedra.ribs.splash](../index.md) / [SplashInteractor](./index.md)

# SplashInteractor

`class SplashInteractor : `[`BaseInteractor`](../../com.uber.rib.core/-base-interactor/index.md)`<`[`SplashPresenter`](-splash-presenter/index.md)`, `[`SplashRouter`](../-splash-router/index.md)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/splash/SplashInteractor.kt#L26)

Coordinates Business Logic for [SplashBuilder.SplashScope](#).

TODO describe the logic of this scope.

### Types

| Name | Summary |
|---|---|
| [SplashPresenter](-splash-presenter/index.md) | `interface SplashPresenter` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SplashInteractor()`<br>Coordinates Business Logic for [SplashBuilder.SplashScope](#). |

### Properties

| Name | Summary |
|---|---|
| [analytics](analytics.md) | `lateinit var analytics: `[`Analytics`](../../com.hedgehog.gdzietabiedra.utils.analytics/-analytics/index.md) |
| [dexter](dexter.md) | `lateinit var dexter: Permission` |
| [presenter](presenter.md) | `lateinit var presenter: `[`SplashPresenter`](-splash-presenter/index.md) |
| [splashListener](splash-listener.md) | `lateinit var splashListener: `[`SplashListener`](../-splash-listener/index.md) |

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
