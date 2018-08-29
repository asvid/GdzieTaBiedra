package com.hedgehog.gdzietabiedra.data.repository.shops

import asvid.github.io.roomapp.data.repository.RxCrudRepository
import com.hedgehog.gdzietabiedra.domain.ShopModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.RealmConfiguration

class ShopsRepository(realmConfiguration: RealmConfiguration) : RxCrudRepository<ShopModel, Long> {

  override fun delete(model: ShopModel): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteAll(models: Collection<ShopModel>): Completable {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fetchAll(): Flowable<Collection<ShopModel>> {
    return Flowable.just(listOf(
        ShopModel(1, "some address", 345, "6:00-22:00"),
        ShopModel(2, "some address", 345, "6:00-22:00"),
        ShopModel(3, "some address", 345, "6:00-22:00"),
        ShopModel(4, "some address", 345, "6:00-22:00"),
        ShopModel(5, "some address", 345, "6:00-22:00")))
  }

  override fun fetchById(id: Long): Single<ShopModel> {
    return Single.create<ShopModel> {
      it.onSuccess(ShopModel(1, "some address", 345, "6:00-22:00"))
    }
  }

  override fun save(model: ShopModel): Single<ShopModel> {
    return Single.create<ShopModel> {
      it.onSuccess(model)
    }
  }

  override fun saveAll(models: Collection<ShopModel>): Single<Collection<ShopModel>> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}