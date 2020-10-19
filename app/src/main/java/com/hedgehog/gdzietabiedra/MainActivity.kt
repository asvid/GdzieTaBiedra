package com.hedgehog.gdzietabiedra

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hedgehog.gdzietabiedra.api.BiedraKtorService
import com.hedgehog.gdzietabiedra.data.repository.shops.toRoomEntity
import com.hedgehog.gdzietabiedra.data.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.time.measureDuration
import timber.log.Timber
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

  private val job = Job()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val navView: BottomNavigationView = findViewById(R.id.nav_view)

    val navController = findNavController(R.id.nav_host_fragment)
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    val appBarConfiguration = AppBarConfiguration(setOf(
        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)

    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "biedra-shops"
    ).build()

    val biedraApi = BiedraKtorService()

    val duration = measureTimeMillis {
      CoroutineScope(Dispatchers.IO).launch {
        val startLat = 48.368519
        val endLat = 54.935336

        val startLng = 13.250631
        val endLng = 25.473183

        var progress = 0f

        val st = 0.08
        for (lat in startLat..endLat step st) {
          for (lng in startLng..endLng step st) {
            Timber.d("checking: $lat|$lng as ${progress++}: ${(progress/13_000)*100}%")
            try {
              val result = withContext(Dispatchers.IO) { biedraApi.getShops(lat, lng) }
              result.shops?.forEach {
                Timber.d("saving: $it")
                db.shopRoomDao().insert(it.toRoomEntity())
              }
            } catch (e: Exception) {
              Timber.e("exception: $e")
            }
          }
        }
      }

    }

    Timber.d("it took just: $duration ms")
  }
}

infix fun ClosedRange<Double>.step(step: Double): Iterable<Double> {
  require(start.isFinite())
  require(endInclusive.isFinite())
  require(step > 0.0) { "Step must be positive, was: $step." }
  val sequence = generateSequence(start) { previous ->
    if (previous == Double.POSITIVE_INFINITY) return@generateSequence null
    val next = previous + step
    if (next > endInclusive) null else next
  }
  return sequence.asIterable()
}

// NW = 54.935336, 13.250631
// SE = 48.368519, 25.473183
// 0,045
