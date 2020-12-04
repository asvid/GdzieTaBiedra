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
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class MapViewModel(
        private val shopService: ShopService,
        private val locationService: LocationService,
) : ViewModel() {

    lateinit var mapProvider: MapProvider
    var initialySelectedShop: Shop? = null

    private val _showNavButton = MutableLiveData<Boolean>()
    val showNavButton: LiveData<Boolean> = _showNavButton
    private val _openNavigation = MutableLiveData<Shop>()
    val openNavigation: LiveData<Shop> = _openNavigation
    private var shopSelected: Shop? = null

    fun mapLoaded(gmapprovider: GoogleMapProvider) {
        mapProvider = gmapprovider
        _showNavButton.postValue(false)

        viewModelScope.launch {
            if (initialySelectedShop == null) {
                when (val position = locationService.getLocation()) {
                    is Success -> moveMapToPosition(position.location)
                    is Error -> TODO()
                    PermissionRequired -> TODO()
                }
            } else {
                initialySelectedShop?.let {
                    mapProvider.selectShop(it)
                    selectShop(it)
                }
            }
        }

        viewModelScope.launch {
            mapProvider.shopMarkerClicked().collect { marker ->
                selectShop(marker.shop)
            }
        }
        viewModelScope.launch {
            mapProvider.userMovedMap().collect { location ->
                removeShopSelection()
                populateWithMarkers(location)
                _showNavButton.postValue(false)
                Timber.d("hide navigation button")
            }
        }
    }

    private fun selectShop(it: Shop) {
        _showNavButton.postValue(true)
        shopSelected = it
    }

    private suspend fun moveMapToPosition(location: Location) {
        mapProvider.clearMap()
        removeShopSelection()
        mapProvider.goToPosition(location)
        populateWithMarkers(location)
    }

    private suspend fun populateWithMarkers(location: Location) {
        val markers = shopService.getShopsInCloseArea(location)
                .map { ShopMarker.create(it) }
        mapProvider.drawMarkers(markers)
    }

    private fun removeShopSelection() {
        shopSelected = null
    }

    fun navigationButtonClicked() {
        _openNavigation.postValue(shopSelected)
    }

    fun mapOpenedForShop(shopId: String?) {
        shopId?.let {
            viewModelScope.launch {
                shopService.getShopById(shopId)?.let {
                    initialySelectedShop = it
                }
            }
        }
    }
}