[com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist](../../index.md) / [ShopsListInteractor](../index.md) / [ShopsListPresenter](./index.md)

# ShopsListPresenter

`interface ShopsListPresenter` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/bottomnav/shopslist/ShopsListInteractor.kt#L119)

### Functions

| Name | Summary |
|---|---|
| [addToList](add-to-list.md) | `abstract fun addToList(shop: `[`Shop`](../../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [clearList](clear-list.md) | `abstract fun clearList(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [displayLocationInfo](display-location-info.md) | `abstract fun displayLocationInfo(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [listItemClicked](list-item-clicked.md) | `abstract fun listItemClicked(): `[`Subject`](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/Subject.html)`<`[`Shop`](../../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`>` |
| [observeSearch](observe-search.md) | `abstract fun observeSearch(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [populateList](populate-list.md) | `abstract fun populateList(shops: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`Shop`](../../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setView](set-view.md) | `abstract fun setView(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [showToast](show-toast.md) | `abstract fun showToast(shop: `[`Shop`](../../../com.hedgehog.gdzietabiedra.domain/-shop/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [ShopsListView](../../-shops-list-view/index.md) | `class ShopsListView : `[`LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout.html)`, `[`ShopsListPresenter`](./index.md)<br>Top level view for {@link ShopsListBuilder.ShopsListScope}. |
