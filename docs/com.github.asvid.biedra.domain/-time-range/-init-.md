[com.github.asvid.biedra.domain](../index.md) / [TimeRange](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`TimeRange(start: LocalTime, length: Duration)`

that takes [LocalTime](#) start and [Duration](#) duration of range

### Parameters

`start` - start date of range

`length` - duration of range

**Constructor**
that takes [LocalTime](#) start and [Duration](#) duration of range

`TimeRange(startDate: `[`Date`](https://developer.android.com/reference/java/util/Date.html)`, endDate: `[`Date`](https://developer.android.com/reference/java/util/Date.html)`)`

that takes standard Java [Date](https://developer.android.com/reference/java/util/Date.html) start and [Date](https://developer.android.com/reference/java/util/Date.html) end

### Parameters

`startDate` - start date of range

`endDate` - end date of range

**Constructor**
that takes standard Java [Date](https://developer.android.com/reference/java/util/Date.html) start and [Date](https://developer.android.com/reference/java/util/Date.html) end

`TimeRange(start: LocalTime, end: LocalTime)`

[TimeRange](index.md) allows easer storing and manipulating time ranges, for example: [Shop](../-shop/index.md) open hours
This class uses [Duration](#) and [LocalTime](#) from JodaTime library, because similar Java classes are available from API26

