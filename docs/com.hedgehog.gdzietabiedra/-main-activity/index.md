[com.hedgehog.gdzietabiedra](../index.md) / [MainActivity](./index.md)

# MainActivity

`class MainActivity : RibActivity` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/MainActivity.kt#L18)

Only [android.app.Activity](https://developer.android.com/reference/android/app/Activity.html) in this app. Its used only to start RIBs and provide it's dependencies

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MainActivity()`<br>Only [android.app.Activity](https://developer.android.com/reference/android/app/Activity.html) in this app. Its used only to start RIBs and provide it's dependencies |

### Properties

| Name | Summary |
|---|---|
| [analytics](analytics.md) | `lateinit var analytics: `[`Analytics`](../../com.hedgehog.gdzietabiedra.utils.analytics/-analytics/index.md) |
| [locationWatchdog](location-watchdog.md) | `lateinit var locationWatchdog: `[`LocationWatchdog`](../../com.hedgehog.gdzietabiedra.appservice/-location-watchdog/index.md) |
| [shopService](shop-service.md) | `lateinit var shopService: `[`ShopService`](../../com.hedgehog.gdzietabiedra.appservice/-shop-service/index.md) |

### Functions

| Name | Summary |
|---|---|
| [createRouter](create-router.md) | `fun createRouter(parentViewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`): ViewRouter<*, *, *>`<br>Builds Root RIB and provides dependencies from [MainActivity](./index.md) |
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Dagger injection and [LocationWatchdog](../../com.hedgehog.gdzietabiedra.appservice/-location-watchdog/index.md) registration |
| [onDestroy](on-destroy.md) | `fun onDestroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Unregisters from [LocationWatchdog](../../com.hedgehog.gdzietabiedra.appservice/-location-watchdog/index.md) |
