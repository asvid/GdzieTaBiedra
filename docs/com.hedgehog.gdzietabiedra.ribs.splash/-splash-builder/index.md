[com.hedgehog.gdzietabiedra.ribs.splash](../index.md) / [SplashBuilder](./index.md)

# SplashBuilder

`class SplashBuilder : ViewBuilder<`[`SplashView`](../-splash-view/index.md)`, `[`SplashRouter`](../-splash-router/index.md)`, `[`ParentComponent`](-parent-component/index.md)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/splash/SplashBuilder.kt#L23)

Builder for the {@link SplashScope}.

TODO describe this scope's responsibility as a whole.

### Types

| Name | Summary |
|---|---|
| [BuilderComponent](-builder-component/index.md) | `interface BuilderComponent` |
| [Component](-component/index.md) | `interface Component : InteractorBaseComponent<`[`SplashInteractor`](../-splash-interactor/index.md)`>, `[`BuilderComponent`](-builder-component/index.md) |
| [Module](-module/index.md) | `abstract class Module` |
| [ParentComponent](-parent-component/index.md) | `interface ParentComponent` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SplashBuilder(dependency: `[`ParentComponent`](-parent-component/index.md)`)`<br>Builder for the {@link SplashScope}. |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | `fun build(parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`SplashRouter`](../-splash-router/index.md)<br>Builds a new [SplashRouter](../-splash-router/index.md). |
| [inflateView](inflate-view.md) | `fun inflateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`SplashView`](../-splash-view/index.md)`?` |
