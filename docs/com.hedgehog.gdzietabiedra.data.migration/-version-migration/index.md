[com.hedgehog.gdzietabiedra.data.migration](../index.md) / [VersionMigration](./index.md)

# VersionMigration

`interface VersionMigration` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/data/migration/VersionMigration.kt#L5)

### Functions

| Name | Summary |
|---|---|
| [migrate](migrate.md) | `abstract fun migrate(realm: DynamicRealm, oldVersion: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [Version0Migration](../-version0-migration/index.md) | `class Version0Migration : `[`VersionMigration`](./index.md) |
