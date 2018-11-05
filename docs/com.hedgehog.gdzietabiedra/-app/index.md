[com.hedgehog.gdzietabiedra](../index.md) / [App](./index.md)

# App

`class App : `[`Application`](https://developer.android.com/reference/android/app/Application.html)`, HasActivityInjector, HasServiceInjector`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `App()` |

### Properties

| Name | Summary |
|---|---|
| [dispatchingAndroidInjector](dispatching-android-injector.md) | `lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<`[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`>` |
| [dispatchingServiceInjector](dispatching-service-injector.md) | `lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<`[`Service`](https://developer.android.com/reference/android/app/Service.html)`>` |

### Functions

| Name | Summary |
|---|---|
| [activityInjector](activity-injector.md) | `fun activityInjector(): AndroidInjector<`[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`>` |
| [onCreate](on-create.md) | `fun onCreate(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [serviceInjector](service-injector.md) | `fun serviceInjector(): AndroidInjector<`[`Service`](https://developer.android.com/reference/android/app/Service.html)`>` |
