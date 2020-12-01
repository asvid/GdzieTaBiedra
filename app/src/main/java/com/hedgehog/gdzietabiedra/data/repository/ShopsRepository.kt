package com.hedgehog.gdzietabiedra.data.repository

import com.github.asvid.biedra.domain.Location
import com.github.asvid.biedra.domain.Shop
import com.hedgehog.gdzietabiedra.data.db.shops.ShopRoomDao
import com.hedgehog.gdzietabiedra.data.db.shops.toDomainModel

class ShopsRepository constructor(private val shopDao: ShopRoomDao) {

    suspend fun fetchAll(): List<Shop> {
        return shopDao.getAll()
                .map { it.toDomainModel() }
    }

    suspend fun fetchById(id: String): Shop? {
        return shopDao.getById(id)?.toDomainModel()
    }

    suspend fun fetchByAddress(address: String): List<Shop> {
        return shopDao.fetchForAddress(address.trim())
                .map { it.toDomainModel() }
    }

    suspend fun fetchByLocationAndRange(
            location: Location,
            range: Double
    ): List<Shop> {
        val minLat = location.lat - range / 2
        val maxLat = location.lat + range / 2
        val minLng = location.lng - range
        val maxLng = location.lng + range

        return shopDao.fetchInRange(minLat, maxLat, minLng, maxLng)
                .map { it.toDomainModel() }
    }
}