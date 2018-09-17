package com.hedgehog.gdzietabiedra.data.repository.shops

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class ShopEntity : RealmObject() {

  @PrimaryKey
  @Required
  var id: String = ""
  @Required
  var shopNumber: Int? = null

  @Required
  lateinit var city: String
  @Required
  var street: String = ""

  var streetNumber: String? = null
  @Required
  var latitude: Long? = null
  @Required
  var longitude: Long? = null
  @Required
  var hours: String? = null

  var hoursFriday: String? = null
  var hoursSaturday: String? = null
  var hoursSunday: String? = null

  var distance: Long? = null

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