[com.hedgehog.gdzietabiedra.ribs](../../index.md) / [RootBuilder](../index.md) / [Component](./index.md)

# Component

`@Component([Module], [ParentComponent]) interface Component : InteractorBaseComponent<`[`RootInteractor`](../../-root-interactor/index.md)`>, `[`BuilderComponent`](../-builder-component/index.md)`, `[`ParentComponent`](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav/-bottom-nav-builder/-parent-component/index.md)`, `[`ParentComponent`](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-builder/-parent-component/index.md)`, `[`ParentComponent`](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.map/-map-builder/-parent-component/index.md)`, `[`ParentComponent`](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.settings/-settings-builder/-parent-component/index.md)`, `[`ParentComponent`](../../../com.hedgehog.gdzietabiedra.ribs.splash/-splash-builder/-parent-component/index.md) [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/RootBuilder.kt#L153)

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | `interface Builder` |

### Inherited Functions

| Name | Summary |
|---|---|
| [analytics](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-builder/-parent-component/analytics.md) | `abstract fun analytics(): `[`Analytics`](../../../com.hedgehog.gdzietabiedra.utils.analytics/-analytics/index.md) |
| [dexter](../../../com.hedgehog.gdzietabiedra.ribs.splash/-splash-builder/-parent-component/dexter.md) | `abstract fun dexter(): Permission` |
| [listener](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-builder/-parent-component/listener.md) | `abstract fun listener(): `[`ShopListListener`](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shop-list-listener/index.md) |
| [locationService](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-builder/-parent-component/location-service.md) | `abstract fun locationService(): `[`LocationWatchdog`](../../../com.hedgehog.gdzietabiedra.appservice/-location-watchdog/index.md) |
| [mapEvents](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.map/-map-builder/-parent-component/map-events.md) | `abstract fun mapEvents(): PublishRelay<`[`MapEvent`](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.map/-map-event/index.md)`>` |
| [navigationListener](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav/-bottom-nav-builder/-parent-component/navigation-listener.md) | `abstract fun navigationListener(): `[`Listener`](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav/-bottom-nav-interactor/-listener/index.md) |
| [rootRouter](../-builder-component/root-router.md) | `abstract fun rootRouter(): `[`RootRouter`](../../-root-router/index.md) |
| [shopServices](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-builder/-parent-component/shop-services.md) | `abstract fun shopServices(): `[`ShopService`](../../../com.hedgehog.gdzietabiedra.appservice/-shop-service/index.md) |
| [splashListener](../../../com.hedgehog.gdzietabiedra.ribs.splash/-splash-builder/-parent-component/splash-listener.md) | `abstract fun splashListener(): `[`SplashListener`](../../../com.hedgehog.gdzietabiedra.ribs.splash/-splash-listener/index.md) |
