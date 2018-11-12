[com.hedgehog.gdzietabiedra.ribs.bottomnav.map](../index.md) / [MapBuilder](./index.md)

# MapBuilder

`class MapBuilder : ViewBuilder<`[`MapView`](../-map-view/index.md)`, `[`MapRouter`](../-map-router/index.md)`, `[`ParentComponent`](-parent-component/index.md)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/bottomnav/map/MapBuilder.kt#L25)

Builder for the {@link MapScope}.

TODO describe this scope's responsibility as a whole.

### Types

| Name | Summary |
|---|---|
| [BuilderComponent](-builder-component/index.md) | `interface BuilderComponent` |
| [Component](-component/index.md) | `interface Component : InteractorBaseComponent<`[`MapInteractor`](../-map-interactor/index.md)`>, `[`BuilderComponent`](-builder-component/index.md) |
| [Module](-module/index.md) | `abstract class Module` |
| [ParentComponent](-parent-component/index.md) | `interface ParentComponent` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MapBuilder(dependency: `[`ParentComponent`](-parent-component/index.md)`)`<br>Builder for the {@link MapScope}. |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | `fun build(parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`MapRouter`](../-map-router/index.md)<br>Builds a new [MapRouter](../-map-router/index.md). |
| [inflateView](inflate-view.md) | `fun inflateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`MapView`](../-map-view/index.md)`?` |
