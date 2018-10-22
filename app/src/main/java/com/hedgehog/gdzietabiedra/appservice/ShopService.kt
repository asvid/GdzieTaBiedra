package com.hedgehog.gdzietabiedra.appservice

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import com.hedgehog.gdzietabiedra.domain.Shop
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

class ShopService @Inject constructor(
    private val shopsRepository: ShopsRepository,
    private val distanceCalculator: DistanceCalculator
) {
  fun getShopsByAddress(address: String, location: Position): Flowable<Shop> {
    Timber.d("looking for shops with address like: $address")
    return shopsRepository.fetchByAddress(address, 50)
        .doOnNext(calculateDistance(location))
  }

  fun getShopsInRange(location: Position, range: Double): Flowable<Shop> {
    return shopsRepository.fetchByLocationAndRange(location, range, 50)
        .doOnNext(calculateDistance(location))
  }

  private fun calculateDistance(location: Position): Consumer<Shop> {
    return Consumer { shop ->
      shop.distance = distanceCalculator.calculateDistance(location, shop.location)
    }
  }
}