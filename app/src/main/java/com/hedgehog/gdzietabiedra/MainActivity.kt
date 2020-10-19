package com.hedgehog.gdzietabiedra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hedgehog.gdzietabiedra.api.BiedraKtorService
import com.hedgehog.gdzietabiedra.data.repository.shops.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

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
        R.id.navigation_list, R.id.navigation_map, R.id.navigation_sundays))
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)

    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "biedra-shops.db"
    ).createFromAsset("biedra-shops.db")
        .build()

    val biedraApi = BiedraKtorService()
    CoroutineScope(Dispatchers.IO).launch {
      db.shopRoomDao().getAll().first().let {
        Timber.d("first element: $it")
      }
    }
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
