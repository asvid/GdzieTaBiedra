package com.hedgehog.gdzietabiedra.data.repository.shops

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.api.response.shop.ShopsItem
import com.github.asvid.biedra.domain.Shop
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.Case
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import timber.log.Timber
import javax.inject.Inject

class ShopsRepository @Inject constructor(private val realmConfiguration: RealmConfiguration) {

  fun fetchAll(): Flowable<Shop> {
    return Realm.getInstance(realmConfiguration)
        .where(ShopEntity::class.java)
        .findAll()
        .asFlowable()
        .flatMapIterable { it -> it }
        .map {
          it.toDomainModel()
        }
  }

  fun fetchById(id: String): Single<Shop> {
    return Single.create<Shop> {
      val realm = Realm.getInstance(realmConfiguration)
      it.onSuccess(realm.where(ShopEntity::class.java)
          .equalTo(ShopEntityFields.ID, id)
          .findFirstAsync().toDomainModel())
      realm.close()
    }
  }

  fun fetchByAddress(
      address: String,
      count: Long): Flowable<Shop> {
    return Realm.getInstance(realmConfiguration)
        .asFlowable().map { realm ->
          realm.where(ShopEntity::class.java)
              .beginGroup()
              .like(ShopEntityFields.STREET, "*$address*", Case.INSENSITIVE)
              .or()
              .like(ShopEntityFields.CITY, "*$address*", Case.INSENSITIVE)
              .endGroup()
              .findAll()
        }
        .flatMapIterable { it -> it }
        .take(count)
        .map {
          it.toDomainModel()
        }
  }

  fun fetchByLocationAndRange(
      location: Position,
      range: Double,
      count: Long
  ): Flowable<Shop> {
    return Realm.getInstance(realmConfiguration)
        .where(ShopEntity::class.java)
        .between(ShopEntityFields.LATITUDE, location.lat - range / 2, location.lat + range / 2)
        .between(ShopEntityFields.LONGITUDE, location.lng - range, location.lng + range)
        .findAll()
        .asFlowable()
        .flatMapIterable { it -> it }
        .take(count)
        .map {
          it.toDomainModel()
        }

  }

  fun save(apiModel: ShopsItem) {
    val realm = Realm.getInstance(realmConfiguration)
    realm.executeTransaction {
      val shopEntity = apiModel.toRealmEntity()
      it.copyToRealmOrUpdate(shopEntity)
    }
    realm.close()
  }

  fun saveAll(apiModels: Collection<ShopsItem>) {
    val realm = Realm.getInstance(realmConfiguration)
    realm.executeTransaction {
      val realmList = RealmList<ShopEntity>()
      realmList.addAll(apiModels.toRealmEntity())
      realm.insertOrUpdate(realmList)
    }
    Timber.d("biedras saved: $apiModels")
    realm.close()
  }
}