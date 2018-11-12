[com.hedgehog.gdzietabiedra.ribs.bottomnav](../../index.md) / [BottomNavInteractor](../index.md) / [BottomNavPresenter](./index.md)

# BottomNavPresenter

`interface BottomNavPresenter` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/bottomnav/BottomNavInteractor.kt#L43)

### Functions

| Name | Summary |
|---|---|
| [menuEvents](menu-events.md) | `abstract fun menuEvents(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`MenuItem`](../../-menu-item/index.md)`>` |
| [setActiveMenuItem](set-active-menu-item.md) | `abstract fun setActiveMenuItem(menuItem: `[`MenuItem`](../../-menu-item/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [BottomNavView](../../-bottom-nav-view/index.md) | `class BottomNavView : BottomNavigationView, `[`BottomNavPresenter`](./index.md)<br>Top level view for {@link BottomNavBuilder.BottomNavScope}. |
