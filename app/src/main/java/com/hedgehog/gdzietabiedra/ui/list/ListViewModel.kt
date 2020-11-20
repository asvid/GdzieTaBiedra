package com.hedgehog.gdzietabiedra.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.asvid.biedra.domain.Shop
import com.hedgehog.gdzietabiedra.appservice.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(
        private val shopService: ShopService,
        private val locationService: LocationService,
) : ViewModel() {

    private val _permissionRequest = MutableLiveData<LocationRequestPermissionResult>()
    val permissionRequest: LiveData<LocationRequestPermissionResult> = _permissionRequest

    private val _shopList = MutableLiveData<List<Shop>>()
    val shopList: LiveData<List<Shop>> = _shopList

    init {
    }

    fun loadData() {
        checkPosition()
    }

    private fun checkPosition() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val position = locationService.getPosition()) {
                is Success -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        _shopList.postValue(shopService.getShopsInRange(position.position, 50.0))
                    }
                }
                is Error -> println("no location available")
                is PermissionRequired -> requestLocationPermission()
                is LocationNotAvailable -> locationNotAvailable()
            }
        }
    }

    private fun locationNotAvailable() {
        _permissionRequest.postValue(NoAvailableLocation)
    }

    private fun requestLocationPermission() {
        _permissionRequest.postValue(RequestLocationPermission)
    }

    fun locationPermissionGranted() {
        checkPosition()
    }

    fun shopSearchQueryChanged(query: String?) {
        if (query.isNullOrEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            _shopList.postValue(shopService.getShopsByAddress(query, null))
        }
    }

    fun shopSearchQuerySubmited(query: String?) {
        if (query.isNullOrEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            _shopList.postValue(shopService.getShopsByAddress(query, null))
        }
    }

    fun shopListItemClicked(it: Shop) {

    }

    fun locationPermissionDenied() {
        _permissionRequest.postValue(LocationPermissionDenied)
    }
}

sealed class LocationRequestPermissionResult
object RequestLocationPermission : LocationRequestPermissionResult()
object LocationPermissionDenied : LocationRequestPermissionResult()
object NoAvailableLocation : LocationRequestPermissionResult()
