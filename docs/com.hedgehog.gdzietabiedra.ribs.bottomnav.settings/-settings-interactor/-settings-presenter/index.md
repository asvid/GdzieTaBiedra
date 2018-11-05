[com.hedgehog.gdzietabiedra.ribs.bottomnav.settings](../../index.md) / [SettingsInteractor](../index.md) / [SettingsPresenter](./index.md)

# SettingsPresenter

`interface SettingsPresenter`

### Functions

| Name | Summary |
|---|---|
| [emailButtonSubject](email-button-subject.md) | `abstract fun emailButtonSubject(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |
| [openEmail](open-email.md) | `abstract fun openEmail(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [openGooglePlay](open-google-play.md) | `abstract fun openGooglePlay(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [rangeChanges](range-changes.md) | `abstract fun rangeChanges(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [rangeSet](range-set.md) | `abstract fun rangeSet(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [setRangeTest](set-range-test.md) | `abstract fun setRangeTest(range: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setVersionName](set-version-name.md) | `abstract fun setVersionName(versionName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [starsButtonSubject](stars-button-subject.md) | `abstract fun starsButtonSubject(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |

### Inheritors

| Name | Summary |
|---|---|
| [SettingsView](../../-settings-view/index.md) | `class SettingsView : `[`LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout.html)`, `[`SettingsPresenter`](./index.md)<br>Top level view for {@link SettingsBuilder.SettingsScope}. |
