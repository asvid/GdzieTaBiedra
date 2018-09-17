package com.hedgehog.gdzietabiedra.data.repository.shops

import com.hedgehog.gdzietabiedra.api.response.shop.ShopsItem
import com.hedgehog.gdzietabiedra.domain.Shop
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import javax.inject.Inject

class ShopsRepository @Inject constructor(private val realmConfiguration: RealmConfiguration) {

  fun fetchAll(): Flowable<Collection<Shop>> {
    return Realm.getInstance(realmConfiguration)
        .where(ShopEntity::class.java)
        .findAll()
        .asFlowable()
        .map {
          it.map {
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
    realm.close()
  }
}

private fun ShopEntity.toDomainModel(): Shop {
  return Shop(this.id, generateAddress(this), this.distance, this.hours)
}

fun generateAddress(shopEntity: ShopEntity): String {
  return "${shopEntity.city}, ${shopEntity.street} ${shopEntity.streetNumber}"
}

private fun Collection<ShopsItem>.toRealmEntity(): Collection<ShopEntity> {
  return this.map { it.toRealmEntity() }
}

private fun ShopsItem.toRealmEntity(): ShopEntity {
  val output = ShopEntity()
  output.id = this.id!!
  output.city = this.city!!
  output.street = this.street!!
  output.streetNumber = this.streetNumber.toString()
  output.shopNumber = this.shopNumber?.toInt()
  output.latitude = this.latitude?.toDouble()
  output.longitude = this.longitude?.toDouble()
  output.hours = this.hours
  output.hoursFriday = this.hoursFriday
  output.hoursSaturday = this.hoursSaturday
  output.hoursSunday = this.hoursSunday
  output.distance = this.distance?.toDouble()

  output.atm = this.atm == "1"
  output.bakery = this.bakery == "1"
  output.relax = this.relax == "1"
  output.cardPayment = this.cardPayment == "1"
  output.isTaxFree = this.isTaxFree == "1"
  output.isEuro = this.isEuro == "1"
  output.isNew = this.jsonMemberNew == "1"
  output.special = this.special?.toInt()
  output.sublease = this.sublease
  return output
}
