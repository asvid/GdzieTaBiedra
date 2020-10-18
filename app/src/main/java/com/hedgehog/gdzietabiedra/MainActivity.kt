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
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import com.hedgehog.gdzietabiedra.data.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

  private val job = Job()

  val db = Room.databaseBuilder(
      applicationContext,
      AppDatabase::class.java, "biedra-shops"
  ).build()

  val realmDb = ShopsRepository()

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

    val biedraApi = BiedraKtorService()
    CoroutineScope(Dispatchers.IO).launch(Dispatchers.Main) {
      Log.d("BIEDRA", "starting HTTP request")
      try {
        val result = withContext(Dispatchers.IO) { biedraApi.getShops(51f, 21f) }
        result.shops?.forEach {
          Log.d("BIEDRA", "result: $it")
        }
      } catch (e: Exception) {
        Log.d("BIEDRA", "exception: $e")

      }
    }
  }
}