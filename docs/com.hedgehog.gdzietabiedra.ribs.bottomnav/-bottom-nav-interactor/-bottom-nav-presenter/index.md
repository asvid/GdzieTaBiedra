[com.hedgehog.gdzietabiedra.ribs.bottomnav](../../index.md) / [BottomNavInteractor](../index.md) / [BottomNavPresenter](./index.md)

# BottomNavPresenter

`interface BottomNavPresenter`

### Functions

| Name | Summary |
|---|---|
| [menuEvents](menu-events.md) | `abstract fun menuEvents(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`MenuItem`](../../-menu-item/index.md)`>` |
| [setActiveMenuItem](set-active-menu-item.md) | `abstract fun setActiveMenuItem(menuItem: `[`MenuItem`](../../-menu-item/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [BottomNavView](../../-bottom-nav-view/index.md) | `class BottomNavView : `[`BottomNavigationView`](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html)`, `[`BottomNavPresenter`](./index.md)<br>Top level view for {@link BottomNavBuilder.BottomNavScope}. |
