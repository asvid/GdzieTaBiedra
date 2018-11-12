[com.hedgehog.gdzietabiedra.ribs.bottomnav.map](../../index.md) / [MapBuilder](../index.md) / [ParentComponent](./index.md)

# ParentComponent

`interface ParentComponent` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/ribs/bottomnav/map/MapBuilder.kt#L50)

### Functions

| Name | Summary |
|---|---|
| [analytics](analytics.md) | `abstract fun analytics(): `[`Analytics`](../../../com.hedgehog.gdzietabiedra.utils.analytics/-analytics/index.md) |
| [locationService](location-service.md) | `abstract fun locationService(): `[`LocationWatchdog`](../../../com.hedgehog.gdzietabiedra.appservice/-location-watchdog/index.md) |
| [mapEvents](map-events.md) | `abstract fun mapEvents(): PublishRelay<`[`MapEvent`](../../-map-event/index.md)`>` |
| [shopServices](shop-services.md) | `abstract fun shopServices(): `[`ShopService`](../../../com.hedgehog.gdzietabiedra.appservice/-shop-service/index.md) |

### Inheritors

| Name | Summary |
|---|---|
| [Component](../../../com.hedgehog.gdzietabiedra.ribs/-root-builder/-component/index.md) | `interface Component : InteractorBaseComponent<`[`RootInteractor`](../../../com.hedgehog.gdzietabiedra.ribs/-root-interactor/index.md)`>, `[`BuilderComponent`](../../../com.hedgehog.gdzietabiedra.ribs/-root-builder/-builder-component/index.md)`, `[`ParentComponent`](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav/-bottom-nav-builder/-parent-component/index.md)`, `[`ParentComponent`](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.shopslist/-shops-list-builder/-parent-component/index.md)`, `[`ParentComponent`](./index.md)`, `[`ParentComponent`](../../../com.hedgehog.gdzietabiedra.ribs.bottomnav.settings/-settings-builder/-parent-component/index.md)`, `[`ParentComponent`](../../../com.hedgehog.gdzietabiedra.ribs.splash/-splash-builder/-parent-component/index.md) |
