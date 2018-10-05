package com.hedgehog.gdzietabiedra.di.modules

import android.content.Context
import com.hedgehog.gdzietabiedra.appservice.LocationService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
  AppModule::class
])
class LocationModule {

  @Singleton
  @Provides
  fun locationService(context: Context): LocationService = LocationService(context)
}