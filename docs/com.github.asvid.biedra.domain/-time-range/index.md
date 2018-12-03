[com.github.asvid.biedra.domain](../index.md) / [TimeRange](./index.md)

# TimeRange

`class TimeRange` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/domain/src/main/java/com/github/asvid/biedra/domain/TimeRange.kt#L15)

[TimeRange](./index.md) allows easer storing and manipulating time ranges, for example: [Shop](../-shop/index.md) open hours
This class uses [Duration](#) and [LocalTime](#) from JodaTime library, because similar Java classes are available from API26

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TimeRange(start: LocalTime, length: Duration)`<br>that takes [LocalTime](#) start and [Duration](#) duration of range`TimeRange(startDate: `[`Date`](https://developer.android.com/reference/java/util/Date.html)`, endDate: `[`Date`](https://developer.android.com/reference/java/util/Date.html)`)`<br>that takes standard Java [Date](https://developer.android.com/reference/java/util/Date.html) start and [Date](https://developer.android.com/reference/java/util/Date.html) end`TimeRange(start: LocalTime, end: LocalTime)`<br>[TimeRange](./index.md) allows easer storing and manipulating time ranges, for example: [Shop](../-shop/index.md) open hours This class uses [Duration](#) and [LocalTime](#) from JodaTime library, because similar Java classes are available from API26 |

### Properties

| Name | Summary |
|---|---|
| [end](end.md) | `val end: LocalTime`<br>end of range |
| [start](start.md) | `val start: LocalTime`<br>beginning of range |

### Functions

| Name | Summary |
|---|---|
| [toHours](to-hours.md) | `fun toHours(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toMinutes](to-minutes.md) | `fun toMinutes(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](to-string.md) | `fun toString(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [withinRange](within-range.md) | `fun withinRange(time: LocalTime): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
