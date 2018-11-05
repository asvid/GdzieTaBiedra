[com.uber.rib.core](./index.md)

## Package com.uber.rib.core

### Types

| Name | Summary |
|---|---|
| [BaseInteractor](-base-interactor/index.md) | `abstract class BaseInteractor<P, R : Router<out Interactor<*, *>, out InteractorBaseComponent<*>>> : Interactor<`[`P`](-base-interactor/index.md#P)`, `[`R`](-base-interactor/index.md#R)`>` |
| [BaseViewBuilder](-base-view-builder/index.md) | `abstract class BaseViewBuilder<ViewType : `[`View`](https://developer.android.com/reference/android/view/View.html)`, RouterT : Router<*, *>, DependencyT> : ViewBuilder<`[`ViewType`](-base-view-builder/index.md#ViewType)`, `[`RouterT`](-base-view-builder/index.md#RouterT)`, `[`DependencyT`](-base-view-builder/index.md#DependencyT)`>` |
| [RestorableRouter](-restorable-router/index.md) | `interface RestorableRouter` |

### Functions

| Name | Summary |
|---|---|
| [getSerializable](get-serializable.md) | `fun <T : `[`Serializable`](https://developer.android.com/reference/java/io/Serializable.html)`> Bundle.getSerializable(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`T`](get-serializable.md#T) |
| [putSerializable](put-serializable.md) | `fun Bundle.putSerializable(key: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, serializable: `[`Serializable`](https://developer.android.com/reference/java/io/Serializable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [toAndroidBundle](to-android-bundle.md) | `fun Bundle.toAndroidBundle(): `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html) |
