package com.hedgehog.gdzietabiedra.data.repository.shops

import com.github.asvid.biedra.domain.Position
import com.github.asvid.biedra.domain.Shop

class ShopsRepository constructor(private val shopDao: ShopRoomDao) {

  suspend fun fetchAll(): List<Shop> {
    return shopDao.getAll()
        .map {
          it.toDomainModel()
        }
  }

  suspend fun fetchById(id: String): Shop {
    return shopDao.getById(id).toDomainModel()
  }

  suspend fun fetchByAddress(
      address: String,
      count: Long): List<Shop> {
    // TODO: create query to search by address
    return listOf()
  }

  suspend fun fetchByLocationAndRange(
      location: Position,
      range: Double,
      count: Long
  ): List<Shop> {
    val minLat = location.lat - range / 2
    val maxLat = location.lat + range / 2
    val minLng = location.lng - range
    val maxLng = location.lng + range

    return shopDao.fetchInRange(minLat, maxLat, minLng, maxLng).map { it.toDomainModel() }
  }
}