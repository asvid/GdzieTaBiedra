[com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist](./index.md)

## Package com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist

### Types

| Name | Summary |
|---|---|
| [ShopListAdapter](-shop-list-adapter/index.md) | `class ShopListAdapter : Adapter<`[`ShopListItemVH`](-shop-list-item-v-h/index.md)`>` |
| [ShopListItemVH](-shop-list-item-v-h/index.md) | `class ShopListItemVH : ViewHolder` |
| [ShopListListener](-shop-list-listener/index.md) | `interface ShopListListener` |
| [ShopsListBuilder](-shops-list-builder/index.md) | `class ShopsListBuilder : ViewBuilder<`[`ShopsListView`](-shops-list-view/index.md)`, `[`ShopsListRouter`](-shops-list-router/index.md)`, `[`ParentComponent`](-shops-list-builder/-parent-component/index.md)`>`<br>Builder for the {@link ShopsListScope}. |
| [ShopsListInteractor](-shops-list-interactor/index.md) | `class ShopsListInteractor : `[`BaseInteractor`](../com.uber.rib.core/-base-interactor/index.md)`<`[`ShopsListPresenter`](-shops-list-interactor/-shops-list-presenter/index.md)`, `[`ShopsListRouter`](-shops-list-router/index.md)`>` |
| [ShopsListRouter](-shops-list-router/index.md) | `class ShopsListRouter : ViewRouter<`[`ShopsListView`](-shops-list-view/index.md)`, `[`ShopsListInteractor`](-shops-list-interactor/index.md)`, `[`Component`](-shops-list-builder/-component/index.md)`>`<br>Adds and removes children of {@link ShopsListBuilder.ShopsListScope}. |
| [ShopsListView](-shops-list-view/index.md) | `class ShopsListView : `[`LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout.html)`, `[`ShopsListPresenter`](-shops-list-interactor/-shops-list-presenter/index.md)<br>Top level view for {@link ShopsListBuilder.ShopsListScope}. |

### Properties

| Name | Summary |
|---|---|
| [RANGE](-r-a-n-g-e.md) | `const val RANGE: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Coordinates Business Logic for [ShopsListScope](#). |
