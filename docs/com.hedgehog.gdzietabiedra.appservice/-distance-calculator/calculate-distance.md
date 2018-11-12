[com.hedgehog.gdzietabiedra.appservice](../index.md) / [DistanceCalculator](index.md) / [calculateDistance](./calculate-distance.md)

# calculateDistance

`fun calculateDistance(pointA: `[`Position`](../../com.github.asvid.biedra.domain/-position/index.md)`, pointB: `[`Position`](../../com.github.asvid.biedra.domain/-position/index.md)`): `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/appservice/DistanceCalculator.kt#L22)

calculates distance in straight line between 2 [Position](../../com.github.asvid.biedra.domain/-position/index.md)s

``` kotlin
val positionA = Position(52.6546, 58.3541)
val positionB = Position(58.46, 57.35)

val distance = DistanceCalculator().calculateDistance(positionA, positionB)
```

### Parameters

`pointA` - first position

`pointB` - second position

**Return**
[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) distance in straight line between 2 [Position](../../com.github.asvid.biedra.domain/-position/index.md)s

