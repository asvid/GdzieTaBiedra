[com.hedgehog.gdzietabiedra.ribs.splash](./index.md)

## Package com.hedgehog.gdzietabiedra.ribs.splash

### Types

| Name | Summary |
|---|---|
| [SplashBuilder](-splash-builder/index.md) | `class SplashBuilder : ViewBuilder<`[`SplashView`](-splash-view/index.md)`, `[`SplashRouter`](-splash-router/index.md)`, `[`ParentComponent`](-splash-builder/-parent-component/index.md)`>`<br>Builder for the {@link SplashScope}. |
| [SplashEvent](-splash-event/index.md) | `sealed class SplashEvent` |
| [SplashInteractor](-splash-interactor/index.md) | `class SplashInteractor : `[`BaseInteractor`](../com.uber.rib.core/-base-interactor/index.md)`<`[`SplashPresenter`](-splash-interactor/-splash-presenter/index.md)`, `[`SplashRouter`](-splash-router/index.md)`>`<br>Coordinates Business Logic for [SplashBuilder.SplashScope](#). |
| [SplashListener](-splash-listener/index.md) | `interface SplashListener` |
| [SplashRouter](-splash-router/index.md) | `class SplashRouter : ViewRouter<`[`SplashView`](-splash-view/index.md)`, `[`SplashInteractor`](-splash-interactor/index.md)`, `[`Component`](-splash-builder/-component/index.md)`>`<br>Adds and removes children of {@link SplashBuilder.SplashScope}. |
| [SplashView](-splash-view/index.md) | `class SplashView : `[`FrameLayout`](https://developer.android.com/reference/android/widget/FrameLayout.html)`, `[`SplashPresenter`](-splash-interactor/-splash-presenter/index.md)<br>Top level view for {@link SplashBuilder.SplashScope}. |
