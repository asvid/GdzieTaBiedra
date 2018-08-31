package com.hedgehog.gdzietabiedra.data.repository.shops

import asvid.github.io.roomapp.data.repository.RxCrudRepository
import com.hedgehog.gdzietabiedra.domain.Shop
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.RealmConfiguration

class ShopsRepository(realmConfiguration: RealmConfiguration) : RxCrudRepository<Shop, Long> {

  override fun delete(model: Shop): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteAll(models: Collection<Shop>): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAll(): Flowable<Collection<Shop>> {
    return Flowable.just(listOf(
        Shop(1, "some address", 345, "6:00-22:00"),
        Shop(2, "some address", 345, "6:00-22:00"),
        Shop(3, "some address", 345, "6:00-22:00"),
        Shop(4, "some address", 345, "6:00-22:00"),
        Shop(5, "some address", 345, "6:00-22:00")))
  }

  override fun fetchById(id: Long): Single<Shop> {
    return Single.create<Shop> {
      it.onSuccess(Shop(1, "some address", 345, "6:00-22:00"))
    }
  }

  override fun save(model: Shop): Single<Shop> {
    return Single.create<Shop> {
      it.onSuccess(model)
    }
  }

  override fun saveAll(models: Collection<Shop>): Single<Collection<Shop>> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}