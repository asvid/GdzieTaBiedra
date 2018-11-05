[com.hedgehog.gdzietabiedra.ribs.bottomnav.map](./index.md)

## Package com.hedgehog.gdzietabiedra.ribs.bottomnav.map

### Types

| Name | Summary |
|---|---|
| [MapBuilder](-map-builder/index.md) | `class MapBuilder : ViewBuilder<`[`MapView`](-map-view/index.md)`, `[`MapRouter`](-map-router/index.md)`, `[`ParentComponent`](-map-builder/-parent-component/index.md)`>`<br>Builder for the {@link MapScope}. |
| [MapEvent](-map-event/index.md) | `sealed class MapEvent` |
| [MapInteractor](-map-interactor/index.md) | `class MapInteractor : `[`BaseInteractor`](../com.uber.rib.core/-base-interactor/index.md)`<`[`MapPresenter`](-map-interactor/-map-presenter/index.md)`, `[`MapRouter`](-map-router/index.md)`>`<br>Coordinates Business Logic for [MapScope](#). |
| [MapRouter](-map-router/index.md) | `class MapRouter : ViewRouter<`[`MapView`](-map-view/index.md)`, `[`MapInteractor`](-map-interactor/index.md)`, `[`Component`](-map-builder/-component/index.md)`>`<br>Adds and removes children of {@link MapBuilder.MapScope}. |
| [MapView](-map-view/index.md) | `class MapView : `[`FrameLayout`](https://developer.android.com/reference/android/widget/FrameLayout.html)`, `[`MapPresenter`](-map-interactor/-map-presenter/index.md)<br>Top level view for {@link MapBuilder.MapScope}. |
