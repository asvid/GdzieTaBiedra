[com.hedgehog.gdzietabiedra.ribs](./index.md)

## Package com.hedgehog.gdzietabiedra.ribs

### Types

| Name | Summary |
|---|---|
| [RootBuilder](-root-builder/index.md) | `class RootBuilder : ViewBuilder<`[`RootView`](-root-view/index.md)`, `[`RootRouter`](-root-router/index.md)`, `[`ParentComponent`](-root-builder/-parent-component/index.md)`>`<br>Builder for the {@link RootScope}. |
| [RootInteractor](-root-interactor/index.md) | `class RootInteractor : `[`BaseInteractor`](../com.uber.rib.core/-base-interactor/index.md)`<`[`RootPresenter`](-root-interactor/-root-presenter.md)`, `[`RootRouter`](-root-router/index.md)`>`<br>Coordinates Business Logic for [RootScope](#). |
| [RootListener](-root-listener/index.md) | `interface RootListener` |
| [RootRouter](-root-router/index.md) | `class RootRouter : ViewRouter<`[`RootView`](-root-view/index.md)`, `[`RootInteractor`](-root-interactor/index.md)`, `[`Component`](-root-builder/-component/index.md)`>`<br>Adds and removes children of {@link RootBuilder.RootScope}. |
| [RootView](-root-view/index.md) | `class RootView : `[`LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout.html)`, `[`RootPresenter`](-root-interactor/-root-presenter.md)<br>Top level view for {@link RootBuilder.RootScope}. |
