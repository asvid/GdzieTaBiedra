[com.hedgehog.gdzietabiedra.ribs.bottomnav](../index.md) / [BottomNavBuilder](./index.md)

# BottomNavBuilder

`class BottomNavBuilder : ViewBuilder<`[`BottomNavView`](../-bottom-nav-view/index.md)`, `[`BottomNavRouter`](../-bottom-nav-router/index.md)`, `[`ParentComponent`](-parent-component/index.md)`>`

Builder for the {@link BottomNavScope}.

TODO describe this scope's responsibility as a whole.

### Types

| Name | Summary |
|---|---|
| [BuilderComponent](-builder-component/index.md) | `interface BuilderComponent` |
| [Component](-component/index.md) | `interface Component : InteractorBaseComponent<`[`BottomNavInteractor`](../-bottom-nav-interactor/index.md)`>, `[`BuilderComponent`](-builder-component/index.md) |
| [Module](-module/index.md) | `abstract class Module` |
| [ParentComponent](-parent-component/index.md) | `interface ParentComponent` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BottomNavBuilder(dependency: `[`ParentComponent`](-parent-component/index.md)`)`<br>Builder for the {@link BottomNavScope}. |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | `fun build(parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`BottomNavRouter`](../-bottom-nav-router/index.md)<br>Builds a new [BottomNavRouter](../-bottom-nav-router/index.md). |
| [inflateView](inflate-view.md) | `fun inflateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`BottomNavView`](../-bottom-nav-view/index.md)`?` |
