package com.hedgehog.gdzietabiedra.appservice

import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import com.github.asvid.biedra.domain.Shop
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.toFlowable
import timber.log.Timber
import javax.inject.Inject

/**
 * Only class that provides access to [Shop]s
 * For the moment it's used only to return [Shop]s from [ShopsRepository], but in future it might be used to manipulate data
 * */
class ShopService @Inject constructor(
    private val shopsRepository: ShopsRepository,
    private val distanceCalculator: DistanceCalculator
) {

  /**
   * @param address - user query for city or street where [Shop] might be
   * @param location - user location to calculate distance to returned [Shop]
   *
   * @return [Flowable] of [Shop]s that fit to query
   * */
  fun getShopsByAddress(address: String, location: Position?): Flowable<Shop> {
    Timber.d("looking for shops with address like: $address")
    return shopsRepository.fetchByAddress(address, 50)
        .doOnNext(calculateDistance(location))
  }

  /**
   * @param location - user location to narrow DB query
   * @param range - range in which shops around [location] will be returned
   *
   * @return [Flowable] of [Shop]s that are in [range] of [location]
   * */
  fun getShopsInRange(location: Position?, range: Double): Flowable<Shop> {
    return if (location == null) listOf<Shop>().toFlowable()
    else shopsRepository.fetchByLocationAndRange(location, range, 50)
        .doOnNext(calculateDistance(location))
  }

  private fun calculateDistance(location: Position?): Consumer<Shop> {
    return Consumer { shop ->
      if (location == null) {
        shop.distance = null
      } else {
        shop.distance = distanceCalculator.calculateDistance(location, shop.location)
      }
    }
  }
}