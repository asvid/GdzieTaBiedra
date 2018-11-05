[com.hedgehog.gdzietabiedra.appservice](../index.md) / [LocationWatchdog](./index.md)

# LocationWatchdog

`class LocationWatchdog`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `LocationWatchdog(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [context](context.md) | `val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html) |

### Functions

| Name | Summary |
|---|---|
| [getLocation](get-location.md) | `fun getLocation(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Position`](file:/home/adam/repo/GdzieTaBiedra/docs/domain/com.github.asvid.biedra.domain/-position/index.md)`>` |
| [locationEnabledSubject](location-enabled-subject.md) | `fun locationEnabledSubject(): `[`BehaviorSubject`](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/BehaviorSubject.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| [register](register.md) | `fun register(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [unregister](unregister.md) | `fun unregister(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
