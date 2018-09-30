package com.hedgehog.gdzietabiedra.data.repository.shops

import com.hedgehog.gdzietabiedra.api.response.shop.ShopsItem
import com.hedgehog.gdzietabiedra.domain.Shop
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import timber.log.Timber
import javax.inject.Inject

class ShopsRepository @Inject constructor(private val realmConfiguration: RealmConfiguration) {

  fun fetchAll(): Flowable<Collection<Shop>> {
    return Realm.getInstance(realmConfiguration)
        .where(ShopEntity::class.java)
        .findAll()
        .asFlowable()
        .map { shops ->
          shops.map {
            it.toDomainModel()
          }
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