[com.hedgehog.gdzietabiedra.appservice](../index.md) / [LocationWatchdog](./index.md)

# LocationWatchdog

`class LocationWatchdog` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/appservice/LocationWatchdog.kt#L21)

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
| [getLocation](get-location.md) | `fun getLocation(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Position`](../../com.github.asvid.biedra.domain/-position/index.md)`>`<br>[BehaviorSubject](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/BehaviorSubject.html) that returns current [Position](../../com.github.asvid.biedra.domain/-position/index.md) of user - default is [WARSAW](#) when no other is known |
| [locationEnabledSubject](location-enabled-subject.md) | `fun locationEnabledSubject(): `[`BehaviorSubject`](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/BehaviorSubject.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>`<br>[BehaviorSubject](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/BehaviorSubject.html) that returns [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) - true if location is enabled by user and false if it's not |
| [register](register.md) | `fun register(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Register to location data change, it should be called ASAP |
| [unregister](unregister.md) | `fun unregister(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Unregister from location data changes, should be called when app is pausing |
