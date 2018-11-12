[com.hedgehog.gdzietabiedra.ribs](../index.md) / [RootRouter](./index.md)

# RootRouter

`class RootRouter : ViewRouter<`[`RootView`](../-root-view/index.md)`, `[`RootInteractor`](../-root-interactor/index.md)`, `[`Component`](../-root-builder/-component/index.md)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/RootRouter.kt#L24)

Adds and removes children of {@link RootBuilder.RootScope}.

TODO describe the possible child configurations of this scope.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `RootRouter(view: `[`RootView`](../-root-view/index.md)`, interactor: `[`RootInteractor`](../-root-interactor/index.md)`, component: `[`Component`](../-root-builder/-component/index.md)`, bottomNavBuilder: `[`BottomNavBuilder`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav/-bottom-nav-builder/index.md)`, shoplistBuilder: `[`ShopsListBuilder`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-builder/index.md)`, mapBuilder: `[`MapBuilder`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.map/-map-builder/index.md)`, settingsBuilder: `[`SettingsBuilder`](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.settings/-settings-builder/index.md)`, splashBuilder: `[`SplashBuilder`](../../com.hedgehog.gdzietabiedra.ribs.splash/-splash-builder/index.md)`)`<br>Adds and removes children of {@link RootBuilder.RootScope}. |

### Functions

| Name | Summary |
|---|---|
| [attachBottomNav](attach-bottom-nav.md) | `fun attachBottomNav(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [attachMap](attach-map.md) | `fun attachMap(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Builds [com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapView](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.map/-map-view/index.md) if it's not already build and shows is |
| [attachMapHidden](attach-map-hidden.md) | `fun attachMapHidden(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Builds [com.hedgehog.gdzietabiedra.ribs.bottomnav.map.MapView](../../com.hedgehog.gdzietabiedra.ribs.bottomnav.map/-map-view/index.md) without showing it |
| [attachSettings](attach-settings.md) | `fun attachSettings(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [attachShopslist](attach-shopslist.md) | `fun attachShopslist(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [attachSplashScreen](attach-splash-screen.md) | `fun attachSplashScreen(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [detachMap](detach-map.md) | `fun detachMap(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [detachSettings](detach-settings.md) | `fun detachSettings(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [detachShopslist](detach-shopslist.md) | `fun detachShopslist(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [detachSplashScreen](detach-splash-screen.md) | `fun detachSplashScreen(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
