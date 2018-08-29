package com.hedgehog.gdzietabiedra.data.repository.shops

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class Shop : RealmObject() {

  @PrimaryKey
  @Required
  var id: Long? = null

  @Required
  var address: String = ""
}