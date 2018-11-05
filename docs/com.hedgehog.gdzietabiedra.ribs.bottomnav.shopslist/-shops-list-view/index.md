[com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist](../index.md) / [ShopsListView](./index.md)

# ShopsListView

`class ShopsListView : `[`LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout.html)`, `[`ShopsListPresenter`](../-shops-list-interactor/-shops-list-presenter/index.md)

Top level view for {@link ShopsListBuilder.ShopsListScope}.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ShopsListView(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`? = null, defStyle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0)`<br>Top level view for {@link ShopsListBuilder.ShopsListScope}. |

### Functions

| Name | Summary |
|---|---|
| [addToList](add-to-list.md) | `fun addToList(shop: `[`Shop`](file:/home/adam/repo/GdzieTaBiedra/docs/domain/com.hedgehog.gdzietabiedra.domain/-shop/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [clearList](clear-list.md) | `fun clearList(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [displayLocationInfo](display-location-info.md) | `fun displayLocationInfo(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [listItemClicked](list-item-clicked.md) | `fun listItemClicked(): `[`Subject`](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/Subject.html)`<`[`Shop`](file:/home/adam/repo/GdzieTaBiedra/docs/domain/com.hedgehog.gdzietabiedra.domain/-shop/index.md)`>` |
| [observeSearch](observe-search.md) | `fun observeSearch(): `[`PublishSubject`](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/PublishSubject.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [populateList](populate-list.md) | `fun populateList(shops: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`Shop`](file:/home/adam/repo/GdzieTaBiedra/docs/domain/com.hedgehog.gdzietabiedra.domain/-shop/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setView](set-view.md) | `fun setView(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [showToast](show-toast.md) | `fun showToast(shop: `[`Shop`](file:/home/adam/repo/GdzieTaBiedra/docs/domain/com.hedgehog.gdzietabiedra.domain/-shop/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
