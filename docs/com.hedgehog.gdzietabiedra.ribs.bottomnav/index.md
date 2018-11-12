[com.hedgehog.gdzietabiedra.ribs.bottomnav](./index.md)

## Package com.hedgehog.gdzietabiedra.ribs.bottomnav

### Types

| Name | Summary |
|---|---|
| [BottomNavBuilder](-bottom-nav-builder/index.md) | `class BottomNavBuilder : ViewBuilder<`[`BottomNavView`](-bottom-nav-view/index.md)`, `[`BottomNavRouter`](-bottom-nav-router/index.md)`, `[`ParentComponent`](-bottom-nav-builder/-parent-component/index.md)`>`<br>Builder for the {@link BottomNavScope}. |
| [BottomNavInteractor](-bottom-nav-interactor/index.md) | `class BottomNavInteractor : `[`BaseInteractor`](../com.uber.rib.core/-base-interactor/index.md)`<`[`BottomNavPresenter`](-bottom-nav-interactor/-bottom-nav-presenter/index.md)`, `[`BottomNavRouter`](-bottom-nav-router/index.md)`>`<br>Coordinates Business Logic for [BottomNavScope](#). |
| [BottomNavRouter](-bottom-nav-router/index.md) | `class BottomNavRouter : ViewRouter<`[`BottomNavView`](-bottom-nav-view/index.md)`, `[`BottomNavInteractor`](-bottom-nav-interactor/index.md)`, `[`Component`](-bottom-nav-builder/-component/index.md)`>`<br>Adds and removes children of {@link BottomNavBuilder.BottomNavScope}. |
| [BottomNavView](-bottom-nav-view/index.md) | `class BottomNavView : BottomNavigationView, `[`BottomNavPresenter`](-bottom-nav-interactor/-bottom-nav-presenter/index.md)<br>Top level view for {@link BottomNavBuilder.BottomNavScope}. |
| [MenuItem](-menu-item/index.md) | `enum class MenuItem` |
