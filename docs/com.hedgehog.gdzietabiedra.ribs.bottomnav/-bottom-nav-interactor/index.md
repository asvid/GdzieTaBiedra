[com.hedgehog.gdzietabiedra.ribs.bottomnav](../index.md) / [BottomNavInteractor](./index.md)

# BottomNavInteractor

`class BottomNavInteractor : `[`BaseInteractor`](../../com.uber.rib.core/-base-interactor/index.md)`<`[`BottomNavPresenter`](-bottom-nav-presenter/index.md)`, `[`BottomNavRouter`](../-bottom-nav-router/index.md)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/bottomnav/BottomNavInteractor.kt#L17)

Coordinates Business Logic for [BottomNavScope](#).

TODO describe the logic of this scope.

### Types

| Name | Summary |
|---|---|
| [BottomNavPresenter](-bottom-nav-presenter/index.md) | `interface BottomNavPresenter` |
| [Listener](-listener/index.md) | `interface Listener` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BottomNavInteractor()`<br>Coordinates Business Logic for [BottomNavScope](#). |

### Properties

| Name | Summary |
|---|---|
| [listener](listener.md) | `lateinit var listener: `[`Listener`](-listener/index.md) |
| [presenter](presenter.md) | `lateinit var presenter: `[`BottomNavPresenter`](-bottom-nav-presenter/index.md) |

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
