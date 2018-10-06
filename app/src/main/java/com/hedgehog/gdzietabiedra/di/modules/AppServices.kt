package com.hedgehog.gdzietabiedra.di.modules

import com.hedgehog.gdzietabiedra.appservice.DistanceCalculator
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
  AppModule::class,
  DataModule::class
])
class AppServices {

  @Singleton
  @Provides
  fun distanceCalculator(): DistanceCalculator = DistanceCalculator()

  @Singleton
  @Provides
  fun shopService(
      shopsRepository: ShopsRepository,
      distanceCalculator: DistanceCalculator
  ): ShopService = ShopService(shopsRepository, distanceCalculator)
}