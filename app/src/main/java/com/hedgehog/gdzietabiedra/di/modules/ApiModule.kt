package com.hedgehog.gdzietabiedra.di.modules

import com.hedgehog.gdzietabiedra.api.BiedraService
import com.hedgehog.gdzietabiedra.api.HttpClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class ApiModule {

  @Singleton
  @Provides
  fun biedraApi(): BiedraService = HttpClient().buildRetrofit().create(BiedraService::class.java)

}