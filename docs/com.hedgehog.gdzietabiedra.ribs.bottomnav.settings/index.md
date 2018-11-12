[com.hedgehog.gdzietabiedra.ribs.bottomnav.settings](./index.md)

## Package com.hedgehog.gdzietabiedra.ribs.bottomnav.settings

### Types

| Name | Summary |
|---|---|
| [SettingsBuilder](-settings-builder/index.md) | `class SettingsBuilder : ViewBuilder<`[`SettingsView`](-settings-view/index.md)`, `[`SettingsRouter`](-settings-router/index.md)`, `[`ParentComponent`](-settings-builder/-parent-component/index.md)`>`<br>Builder for the {@link SettingsScope}. |
| [SettingsInteractor](-settings-interactor/index.md) | `class SettingsInteractor : `[`BaseInteractor`](../com.uber.rib.core/-base-interactor/index.md)`<`[`SettingsPresenter`](-settings-interactor/-settings-presenter/index.md)`, `[`SettingsRouter`](-settings-router/index.md)`>`<br>Coordinates Business Logic for [SettingsBuilder.SettingsScope](#). |
| [SettingsRouter](-settings-router/index.md) | `class SettingsRouter : ViewRouter<`[`SettingsView`](-settings-view/index.md)`, `[`SettingsInteractor`](-settings-interactor/index.md)`, `[`Component`](-settings-builder/-component/index.md)`>`<br>Adds and removes children of {@link SettingsBuilder.SettingsScope}. |
| [SettingsView](-settings-view/index.md) | `class SettingsView : `[`LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout.html)`, `[`SettingsPresenter`](-settings-interactor/-settings-presenter/index.md)<br>Top level view for {@link SettingsBuilder.SettingsScope}. |
