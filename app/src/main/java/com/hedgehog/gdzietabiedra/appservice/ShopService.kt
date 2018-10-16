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
  fun getShopsByAddress(address: String, location: Position): Flowable<Collection<Shop>> {
    Timber.d("looking for shops with address like: $address")
    return shopsRepository.fetchByAddress(address)
        .doOnNext(calculateDistance(location))
  }

  fun getShopsInRange(location: Position, range: Double): Flowable<Collection<Shop>> {
    return shopsRepository.fetchByLocationAndRange(location, range)
        .doOnNext(calculateDistance(location))
  }

  private fun calculateDistance(location: Position): Consumer<Collection<Shop>> {
    return Consumer { collection ->
      collection.map {
        it.distance = distanceCalculator.calculateDistance(location, it.location)
      }
    }
  }
}