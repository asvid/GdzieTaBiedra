[com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist](../index.md) / [ShopListAdapter](./index.md)

# ShopListAdapter

`class ShopListAdapter : Adapter<`[`ShopListItemVH`](../-shop-list-item-v-h/index.md)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/bottomnav/shopslist/ShopListAdapter.kt#L17)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ShopListAdapter()` |

### Properties

| Name | Summary |
|---|---|
| [itemClickSubject](item-click-subject.md) | `val itemClickSubject: `[`PublishSubject`](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/PublishSubject.html)`<`[`Shop`](../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`>` |
| [itemMoreClicked](item-more-clicked.md) | `val itemMoreClicked: `[`PublishSubject`](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/PublishSubject.html)`<`[`Shop`](../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [addItem](add-item.md) | `fun addItem(shop: `[`Shop`](../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [clearItems](clear-items.md) | `fun clearItems(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`ShopListItemVH`](../-shop-list-item-v-h/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ShopListItemVH`](../-shop-list-item-v-h/index.md) |
| [updateData](update-data.md) | `fun updateData(list: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Shop`](../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
