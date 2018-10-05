package com.hedgehog.gdzietabiedra.data.repository.shops

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import javax.annotation.Nullable

@RealmClass
open class ShopEntity : RealmObject() {

  @PrimaryKey
  @Required
  var id: String = ""
  @Nullable
  var shopNumber: Int? = null

  @Required
  lateinit var city: String
  @Required
  var street: String = ""

  var streetNumber: String? = null
  var latitude: Double = 0.0
  var longitude: Double = 0.0
  @Required
  lateinit var hours: String

  var hoursFriday: String? = null
  var hoursSaturday: String? = null
  var hoursSunday: String? = null

  var distance: Double? = null

  var bakery: Boolean = false
  var relax: Boolean = false
  var atm: Boolean = false
  var cardPayment: Boolean = false
  var isTaxFree: Boolean = false
  var isEuro: Boolean = false
  var isNew: Boolean = false
  var special: Int? = null
  var sublease: String? = null
}