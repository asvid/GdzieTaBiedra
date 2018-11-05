[com.hedgehog.gdzietabiedra.di.modules](../index.md) / [DataModule](./index.md)

# DataModule

`class DataModule`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DataModule()` |

### Functions

| Name | Summary |
|---|---|
| [provideRealmConfiguration](provide-realm-configuration.md) | `fun provideRealmConfiguration(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, versionMigrations: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, Provider<`[`VersionMigration`](../../com.hedgehog.gdzietabiedra.data.migration/-version-migration/index.md)`>>): RealmConfiguration` |
| [shopsRepository](shops-repository.md) | `fun shopsRepository(realmConfiguration: RealmConfiguration): `[`ShopsRepository`](../../com.hedgehog.gdzietabiedra.data.repository.shops/-shops-repository/index.md) |
