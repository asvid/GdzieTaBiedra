[com.uber.rib.core](../index.md) / [BaseViewBuilder](./index.md)

# BaseViewBuilder

`abstract class BaseViewBuilder<ViewType : `[`View`](https://developer.android.com/reference/android/view/View.html)`, RouterT : Router<*, *>, DependencyT> : ViewBuilder<`[`ViewType`](index.md#ViewType)`, `[`RouterT`](index.md#RouterT)`, `[`DependencyT`](index.md#DependencyT)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/uber/rib/core/BaseViewBuilder.kt#L7)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BaseViewBuilder(dependency: `[`DependencyT`](index.md#DependencyT)`)` |

### Functions

| Name | Summary |
|---|---|
| [build](build.md) | `abstract fun build(parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): `[`RouterT`](index.md#RouterT) |
