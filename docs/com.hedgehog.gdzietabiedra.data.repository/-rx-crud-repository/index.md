[com.hedgehog.gdzietabiedra.data.repository](../index.md) / [RxCrudRepository](./index.md)

# RxCrudRepository

`interface RxCrudRepository<M, I> : `[`Repository`](../-repository.md)`<`[`M`](index.md#M)`, `[`I`](index.md#I)`>` [(source)](https://github.com/asvid/GdzieTaBiedra/tree/master/app/src/main/java/com/hedgehog/gdzietabiedra/data/repository/RxCrudRepository.kt#L7)

### Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | `abstract fun delete(model: `[`M`](index.md#M)`): `[`Completable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Completable.html) |
| [deleteAll](delete-all.md) | `abstract fun deleteAll(models: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`M`](index.md#M)`>): `[`Completable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Completable.html) |
| [fetchAll](fetch-all.md) | `abstract fun fetchAll(): `[`Flowable`](http://reactivex.io/RxJava/javadoc/io/reactivex/Flowable.html)`<`[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`M`](index.md#M)`>>` |
| [fetchById](fetch-by-id.md) | `abstract fun fetchById(id: `[`I`](index.md#I)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`M`](index.md#M)`>` |
| [save](save.md) | `abstract fun save(model: `[`M`](index.md#M)`): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`M`](index.md#M)`>` |
| [saveAll](save-all.md) | `abstract fun saveAll(models: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`M`](index.md#M)`>): `[`Single`](http://reactivex.io/RxJava/javadoc/io/reactivex/Single.html)`<`[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`M`](index.md#M)`>>` |
