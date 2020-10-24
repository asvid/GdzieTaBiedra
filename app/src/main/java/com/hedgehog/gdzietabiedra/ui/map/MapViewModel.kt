package com.hedgehog.gdzietabiedra.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.asvid.biedra.domain.Position
import com.hedgehog.gdzietabiedra.appservice.Error
import com.hedgehog.gdzietabiedra.appservice.LocationWatchdogCoroutines
import com.hedgehog.gdzietabiedra.appservice.PermissionRequired
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.appservice.Success
import com.hedgehog.gdzietabiedra.appservice.map.GoogleMapProvider
import com.hedgehog.gdzietabiedra.appservice.map.MapProvider
import com.hedgehog.gdzietabiedra.appservice.map.ShopMarker
import kotlinx.coroutines.launch

class MapViewModel(
    private val shopService: ShopService,
    private val locationWatchdogCoroutines: LocationWatchdogCoroutines,
) : ViewModel() {

  lateinit var mapProvider: MapProvider

  fun mapLoaded(gmapprovider: GoogleMapProvider) {
    mapProvider = gmapprovider
    println("map loaded")

    viewModelScope.launch {
      when (val position = locationWatchdogCoroutines.getPosition()) {
        is Success -> populateWithMarkers(position.position)
        is Error -> TODO()
        PermissionRequired -> TODO()
      }
    }

    mapProvider.shopMarkerClicked()
  }

  private suspend fun populateWithMarkers(position: Position) {
    mapProvider.goToPosition(position)
    shopService.getShopsInRange(position, 0.1)
        .forEach {
          mapProvider.drawMarker(ShopMarker.create(it), false)
        }
  }
}