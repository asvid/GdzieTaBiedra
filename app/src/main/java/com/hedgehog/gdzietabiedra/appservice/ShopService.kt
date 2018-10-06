package com.hedgehog.gdzietabiedra.appservice

import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import com.hedgehog.gdzietabiedra.domain.Point
import com.hedgehog.gdzietabiedra.domain.Shop
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class ShopService @Inject constructor(
    private val shopsRepository: ShopsRepository,
    private val distanceCalculator: DistanceCalculator
) {
  fun getShopsByAddress(address: String): Flowable<Collection<Shop>> {
    return shopsRepository.fetchByAddress(address)
  }

  fun getShopsInRange(location: Point, range: Double): Flowable<Collection<Shop>> {
    return shopsRepository.fetchByLocationAndRange(location, range)
        .doOnNext(calculateDistance(location))
  }

  private fun calculateDistance(location: Point): Consumer<Collection<Shop>> {
    return Consumer { collection ->
      collection.map {
        it.distance = distanceCalculator.calculateDistance(location, it.location)
      }
    }
  }
}