package com.hedgehog.gdzietabiedra.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.asvid.biedra.domain.Position
import com.github.asvid.biedra.domain.Shop
import com.hedgehog.gdzietabiedra.appservice.Error
import com.hedgehog.gdzietabiedra.appservice.LocationWatchdogCoroutines
import com.hedgehog.gdzietabiedra.appservice.PermissionRequired
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.appservice.Success
import com.hedgehog.gdzietabiedra.appservice.map.GoogleMapProvider
import com.hedgehog.gdzietabiedra.appservice.map.MapProvider
import com.hedgehog.gdzietabiedra.appservice.map.ShopMarker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class MapViewModel(
    private val shopService: ShopService,
    private val locationWatchdogCoroutines: LocationWatchdogCoroutines,
) : ViewModel() {

  lateinit var mapProvider: MapProvider

  private val _showNavButton = MutableLiveData<Boolean>()
  val showNavButton: LiveData<Boolean> = _showNavButton
  private val _openNavigation = MutableLiveData<Shop>()
  val openNavigation: LiveData<Shop> = _openNavigation
  private var shopSelected: Shop? = null

  fun mapLoaded(gmapprovider: GoogleMapProvider) {
    mapProvider = gmapprovider
    _showNavButton.postValue(false)

    viewModelScope.launch {
      when (val position = locationWatchdogCoroutines.getPosition()) {
        is Success -> goToPosition(position.position)
        is Error -> TODO()
        PermissionRequired -> TODO()
      }
    }

    viewModelScope.launch {
      mapProvider.shopMarkerClicked().collect { marker ->
        _showNavButton.postValue(true)
        shopSelected = marker.shop
      }
    }
    viewModelScope.launch {
      mapProvider.userMovedMap().collect { position ->
        removeShopSelection()
        populateWithMarkers(position)
        _showNavButton.postValue(false)
      }
    }
  }

  private suspend fun goToPosition(position: Position) {
    mapProvider.goToPosition(position)
    populateWithMarkers(position)
  }

  private suspend fun populateWithMarkers(position: Position) {
    shopService.getShopsInRange(position, 0.1)
        .forEach {
          mapProvider.drawMarker(ShopMarker.create(it), false)
        }
  }

  private fun removeShopSelection() {
    shopSelected = null
  }

  fun navigationButtonClicked() {
    _openNavigation.postValue(shopSelected)
  }
}