package com.hedgehog.gdzietabiedra.ui.list.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.asvid.biedra.domain.shops.Shop
import com.hedgehog.gdzietabiedra.appservice.*
import com.hedgehog.gdzietabiedra.appservice.map.MapProvider
import kotlinx.coroutines.launch

class ShopDetailsViewModel(
        private val shopService: ShopService,
        private val locationService: LocationService
) : ViewModel() {

    lateinit var mapProvider: MapProvider
    private val _shopData = MutableLiveData<Shop>()
    val shopData: LiveData<Shop> = _shopData

    fun openedFromList(shopId: String) {
        viewModelScope.launch {
            _shopData.postValue(
                    when (val location = locationService.getLocation()) {
                        is Success -> shopService.getShopById(shopId, location.location)
                        else -> shopService.getShopById(shopId)
                    })
        }
    }

    fun mapLoaded(mapProvider: MapProvider) {
        this.mapProvider = mapProvider
        viewModelScope.launch {
            _shopData.observeForever {
                mapProvider.showSingleShop(it)
            }
        }
    }
}