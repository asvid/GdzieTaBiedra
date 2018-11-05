[com.hedgehog.gdzietabiedra.ribs.bottomnav](../index.md) / [BottomNavView](./index.md)

# BottomNavView

`class BottomNavView : `[`BottomNavigationView`](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html)`, `[`BottomNavPresenter`](../-bottom-nav-interactor/-bottom-nav-presenter/index.md)

Top level view for {@link BottomNavBuilder.BottomNavScope}.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BottomNavView(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`? = null, defStyle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0)`<br>Top level view for {@link BottomNavBuilder.BottomNavScope}. |

### Functions

| Name | Summary |
|---|---|
| [menuEvents](menu-events.md) | `fun menuEvents(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`MenuItem`](../-menu-item/index.md)`>` |
| [onFinishInflate](on-finish-inflate.md) | `fun onFinishInflate(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setActiveMenuItem](set-active-menu-item.md) | `fun setActiveMenuItem(menuItem: `[`MenuItem`](../-menu-item/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
