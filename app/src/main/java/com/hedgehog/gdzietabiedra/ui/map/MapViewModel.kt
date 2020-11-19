package com.hedgehog.gdzietabiedra.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.asvid.biedra.domain.Location
import com.github.asvid.biedra.domain.Shop
import com.hedgehog.gdzietabiedra.appservice.*
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
        private val locationService: LocationService,
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
            when (val position = locationService.getPosition()) {
                is Success -> goToPosition(position.location)
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

    private suspend fun goToPosition(location: Location) {
        mapProvider.goToPosition(location)
        populateWithMarkers(location)
    }

    private suspend fun populateWithMarkers(location: Location) {
        shopService.getShopsInRange(location, 0.1)
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