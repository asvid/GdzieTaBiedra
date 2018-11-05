package com.hedgehog.gdzietabiedra.data.migration

import io.realm.DynamicRealm

interface VersionMigration {
    fun migrate(realm: DynamicRealm, oldVersion: Long)
}