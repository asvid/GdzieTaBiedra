[com.uber.rib.core](../index.md) / [BaseInteractor](./index.md)

# BaseInteractor

`abstract class BaseInteractor<P, R : Router<out Interactor<*, *>, out InteractorBaseComponent<*>>> : Interactor<`[`P`](index.md#P)`, `[`R`](index.md#R)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/uber/rib/core/BaseInteractor.kt#L10)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BaseInteractor()` |

### Properties

| Name | Summary |
|---|---|
| [modelKey](model-key.md) | `val modelKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [addDisposable](add-disposable.md) | `fun addDisposable(disposable: `[`Disposable`](http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/Disposable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [addToDisposables](add-to-disposables.md) | `fun `[`Disposable`](http://reactivex.io/RxJava/javadoc/io/reactivex/disposables/Disposable.html)`.addToDisposables(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [didBecomeActive](did-become-active.md) | `open fun didBecomeActive(savedInstanceState: Bundle?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getRibName](get-rib-name.md) | `abstract fun getRibName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [onSaveInstanceState](on-save-instance-state.md) | `open fun onSaveInstanceState(outState: Bundle): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [restoreRouter](restore-router.md) | `open fun <T : Router<out Interactor<*, *>, out InteractorBaseComponent<*>>> restoreRouter(clazz: `[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<`[`T`](restore-router.md#T)`>, childInfo: `[`Serializable`](https://developer.android.com/reference/java/io/Serializable.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [willResignActive](will-resign-active.md) | `open fun willResignActive(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [BottomNavInteractor](../../com.hedgehog.gdzietabiedra.ribs.bottomnav/-bottom-nav-interactor/index.md) | `class BottomNavInteractor : `[`BaseInteractor`](./index.md)`<`[`BottomNavPresenter`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav/-bottom-nav-interactor/-bottom-nav-presenter/index.md)`, `[`BottomNavRouter`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav/-bottom-nav-router/index.md)`>`<br>Coordinates Business Logic for [BottomNavBuilder.BottomNavScope](#). |
| [MapInteractor](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.map/-map-interactor/index.md) | `class MapInteractor : `[`BaseInteractor`](./index.md)`<`[`MapPresenter`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.map/-map-interactor/-map-presenter/index.md)`, `[`MapRouter`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.map/-map-router/index.md)`>`<br>Coordinates Business Logic for [MapBuilder.MapScope](#). |
| [RootInteractor](../../com.hedgehog.gdzietabiedra.ribs/-root-interactor/index.md) | `class RootInteractor : `[`BaseInteractor`](./index.md)`<`[`RootPresenter`](../../com.hedgehog.gdzietabiedra.ribs/-root-interactor/-root-presenter.md)`, `[`RootRouter`](../../com.hedgehog.gdzietabiedra.ribs/-root-router/index.md)`>`<br>Coordinates Business Logic for [RootBuilder.RootScope](#). |
| [SettingsInteractor](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.settings/-settings-interactor/index.md) | `class SettingsInteractor : `[`BaseInteractor`](./index.md)`<`[`SettingsPresenter`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.settings/-settings-interactor/-settings-presenter/index.md)`, `[`SettingsRouter`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.settings/-settings-router/index.md)`>`<br>Coordinates Business Logic for [SettingsBuilder.SettingsScope](#). |
| [ShopsListInteractor](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-interactor/index.md) | `class ShopsListInteractor : `[`BaseInteractor`](./index.md)`<`[`ShopsListPresenter`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-interactor/-shops-list-presenter/index.md)`, `[`ShopsListRouter`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-router/index.md)`>` |
| [SplashInteractor](../../com.hedgehog.gdzietabiedra.ribs.splash/-splash-interactor/index.md) | `class SplashInteractor : `[`BaseInteractor`](./index.md)`<`[`SplashPresenter`](../../com.hedgehog.gdzietabiedra.ribs.splash/-splash-interactor/-splash-presenter/index.md)`, `[`SplashRouter`](../../com.hedgehog.gdzietabiedra.ribs.splash/-splash-router/index.md)`>`<br>Coordinates Business Logic for [SplashBuilder.SplashScope](#). |
