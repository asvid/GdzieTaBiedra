package com.hedgehog.gdzietabiedra

import android.app.Application
import androidx.room.Room
import com.hedgehog.gdzietabiedra.api.BiedraKtorService
import com.hedgehog.gdzietabiedra.appservice.DistanceCalculator
import com.hedgehog.gdzietabiedra.appservice.LocationWatchdogCoroutines
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.data.repository.shops.AppDatabase
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import com.hedgehog.gdzietabiedra.ui.list.ListViewModel
import com.hedgehog.gdzietabiedra.ui.map.MapViewModel
import com.hedgehog.gdzietabiedra.ui.sundays.SundaysViewModel
import com.hedgehog.gdzietabiedra.utils.CrashlyticsTree
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Application class, no rocket science here
 * */
class App : Application() {

  /**
   * [LeakCanary] initialisation
   * [Timber] initialisation
   * */
  override fun onCreate() {
    super.onCreate()

    initTimber()
    initJoda()
    initKoin()
  }

  private fun initKoin() {
    startKoin {
      androidContext(this@App)
      modules(module {
        single<AppDatabase> {
          Room.databaseBuilder(
                  applicationContext,
                  AppDatabase::class.java, "biedra-shops.db"
          ).createFromAsset("biedra-shops.db")
                  .build()
        }
        single { DistanceCalculator() }
        single { ShopService(ShopsRepository(get<AppDatabase>().shopRoomDao()), get()) }
        single { BiedraKtorService() }
        single { LocationWatchdogCoroutines(get()) }

        viewModel { ListViewModel(get(), get()) }
        viewModel { MapViewModel(get(), get()) }
        viewModel { SundaysViewModel() }
      })
    }
  }

  private fun initJoda() {
    JodaTimeAndroid.init(this)
  }

  private fun initTimber() {
    Timber.plant(DebugTree(), CrashlyticsTree())
  }
}