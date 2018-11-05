[com.hedgehog.gdzietabiedra.ribs.bottomnav.settings](../index.md) / [SettingsView](./index.md)

# SettingsView

`class SettingsView : `[`LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout.html)`, `[`SettingsPresenter`](../-settings-interactor/-settings-presenter/index.md)

Top level view for {@link SettingsBuilder.SettingsScope}.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SettingsView(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`? = null, defStyle: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0)`<br>Top level view for {@link SettingsBuilder.SettingsScope}. |

### Functions

| Name | Summary |
|---|---|
| [emailButtonSubject](email-button-subject.md) | `fun emailButtonSubject(): `[`PublishSubject`](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/PublishSubject.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |
| [onFinishInflate](on-finish-inflate.md) | `fun onFinishInflate(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [openEmail](open-email.md) | `fun openEmail(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [openGooglePlay](open-google-play.md) | `fun openGooglePlay(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [rangeChanges](range-changes.md) | `fun rangeChanges(): `[`Observable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Observable.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [rangeSet](range-set.md) | `fun rangeSet(): `[`PublishSubject`](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/PublishSubject.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [setRangeTest](set-range-test.md) | `fun setRangeTest(range: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setVersionName](set-version-name.md) | `fun setVersionName(versionName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [starsButtonSubject](stars-button-subject.md) | `fun starsButtonSubject(): `[`PublishSubject`](http://reactivex.io/RxJava/javadoc/io/reactivex/subjects/PublishSubject.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |
