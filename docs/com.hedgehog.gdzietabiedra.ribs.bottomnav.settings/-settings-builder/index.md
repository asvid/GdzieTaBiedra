[com.hedgehog.gdzietabiedra.ribs.bottomnav.settings](../index.md) / [SettingsBuilder](./index.md)

# SettingsBuilder

`class SettingsBuilder : ViewBuilder<`[`SettingsView`](../-settings-view/index.md)`, `[`SettingsRouter`](../-settings-router/index.md)`, `[`ParentComponent`](-parent-component/index.md)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/bottomnav/settings/SettingsBuilder.kt#L22)

Builder for the {@link SettingsScope}.

TODO describe this scope's responsibility as a whole.

### Types

| Name | Summary |
|---|---|
| [BuilderComponent](-builder-component/index.md) | `interface BuilderComponent` |
| [Component](-component/index.md) | `interface Component : InteractorBaseComponent<`[`SettingsInteractor`](../-settings-interactor/index.md)`>, `[`BuilderComponent`](-builder-component/index.md) |
| [Module](-module/index.md) | `abstract class Module` |
| [ParentComponent](-parent-component/index.md) | `interface ParentComponent` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SettingsBuilder(dependency: `[`ParentComponent`](-parent-component/index.md)`)`<br>Builder for the {@link SettingsScope}. |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | `fun build(parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`SettingsRouter`](../-settings-router/index.md)<br>Builds a new [SettingsRouter](../-settings-router/index.md). |
| [inflateView](inflate-view.md) | `fun inflateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`SettingsView`](../-settings-view/index.md)`?` |
