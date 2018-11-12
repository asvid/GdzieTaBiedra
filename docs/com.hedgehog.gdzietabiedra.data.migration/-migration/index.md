[com.hedgehog.gdzietabiedra.data.migration](../index.md) / [Migration](./index.md)

# Migration

`@Reusable class Migration : RealmMigration` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/data/migration/Migration.kt#L11)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Migration(versionMigrations: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, Provider<`[`VersionMigration`](../-version-migration/index.md)`>>)` |

### Functions

| Name | Summary |
|---|---|
| [migrate](migrate.md) | `fun migrate(realm: DynamicRealm, oldVersion: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, newVersion: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
