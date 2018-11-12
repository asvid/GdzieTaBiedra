[com.hedgehog.gdzietabiedra.ribs](../index.md) / [RootBuilder](./index.md)

# RootBuilder

`class RootBuilder : ViewBuilder<`[`RootView`](../-root-view/index.md)`, `[`RootRouter`](../-root-router/index.md)`, `[`ParentComponent`](-parent-component/index.md)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/RootBuilder.kt#L40)

Builder for the {@link RootScope}.

TODO describe this scope's responsibility as a whole.

### Types

| Name | Summary |
|---|---|
| [BuilderComponent](-builder-component/index.md) | `interface BuilderComponent` |
| [Component](-component/index.md) | `interface Component : InteractorBaseComponent<`[`RootInteractor`](../-root-interactor/index.md)`>, `[`BuilderComponent`](-builder-component/index.md)`, `[`ParentComponent`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav/-bottom-nav-builder/-parent-component/index.md)`, `[`ParentComponent`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-builder/-parent-component/index.md)`, `[`ParentComponent`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.map/-map-builder/-parent-component/index.md)`, `[`ParentComponent`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.settings/-settings-builder/-parent-component/index.md)`, `[`ParentComponent`](../../com.hedgehog.gdzietabiedra.ribs.splash/-splash-builder/-parent-component/index.md) |
| [Module](-module/index.md) | `abstract class Module` |
| [ParentComponent](-parent-component/index.md) | `interface ParentComponent` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RootBuilder(dependency: `[`ParentComponent`](-parent-component/index.md)`)`<br>Builder for the {@link RootScope}. |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | `fun build(parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`RootRouter`](../-root-router/index.md)<br>Builds a new [RootRouter](../-root-router/index.md). |
| [inflateView](inflate-view.md) | `fun inflateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`RootView`](../-root-view/index.md)`?` |
