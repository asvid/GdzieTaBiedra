[com.github.asvid.biedra.domain](../index.md) / [OpenHours](./index.md)

# OpenHours

`data class OpenHours` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/domain/src/main/java/com/github/asvid/biedra/domain/OpenHours.kt#L16)

[Shop](../-shop/index.md) open hours, contains [TimeRange](../-time-range/index.md) for weekdays, saturday and sunday

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `OpenHours(weekDay: `[`TimeRange`](../-time-range/index.md)`, saturday: `[`TimeRange`](../-time-range/index.md)`?, sunday: `[`TimeRange`](../-time-range/index.md)`?)`<br>[Shop](../-shop/index.md) open hours, contains [TimeRange](../-time-range/index.md) for weekdays, saturday and sunday |

### Properties

| Name | Summary |
|---|---|
| [saturday](saturday.md) | `val saturday: `[`TimeRange`](../-time-range/index.md)`?`<br>opening hours |
| [sunday](sunday.md) | `val sunday: `[`TimeRange`](../-time-range/index.md)`?`<br>opening hours |
| [weekDay](week-day.md) | `val weekDay: `[`TimeRange`](../-time-range/index.md)<br>opening hours for monday - friday |

### Extension Functions

| Name | Summary |
|---|---|
| [getForToday](../get-for-today.md) | `fun `[`OpenHours`](./index.md)`.getForToday(): `[`TimeRange`](../-time-range/index.md) |
