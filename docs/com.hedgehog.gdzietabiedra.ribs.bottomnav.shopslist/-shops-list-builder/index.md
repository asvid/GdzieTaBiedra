[com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist](../index.md) / [ShopsListBuilder](./index.md)

# ShopsListBuilder

`class ShopsListBuilder : ViewBuilder<`[`ShopsListView`](../-shops-list-view/index.md)`, `[`ShopsListRouter`](../-shops-list-router/index.md)`, `[`ParentComponent`](-parent-component/index.md)`>`

Builder for the {@link ShopsListScope}.

TODO describe this scope's responsibility as a whole.

### Types

| Name | Summary |
|---|---|
| [BuilderComponent](-builder-component/index.md) | `interface BuilderComponent` |
| [Component](-component/index.md) | `interface Component : InteractorBaseComponent<`[`ShopsListInteractor`](../-shops-list-interactor/index.md)`>, `[`BuilderComponent`](-builder-component/index.md) |
| [Module](-module/index.md) | `abstract class Module` |
| [ParentComponent](-parent-component/index.md) | `interface ParentComponent` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ShopsListBuilder(dependency: `[`ParentComponent`](-parent-component/index.md)`)`<br>Builder for the {@link ShopsListScope}. |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | `fun build(parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`ShopsListRouter`](../-shops-list-router/index.md)<br>Builds a new [ShopsListRouter](../-shops-list-router/index.md). |
| [inflateView](inflate-view.md) | `fun inflateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`ShopsListView`](../-shops-list-view/index.md)`?` |
