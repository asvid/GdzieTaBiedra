package com.hedgehog.gdzietabiedra.ui.list

import android.Manifest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.asvid.biedra.domain.Shop
import com.hedgehog.gdzietabiedra.appservice.Error
import com.hedgehog.gdzietabiedra.appservice.LocationWatchdogCoroutines
import com.hedgehog.gdzietabiedra.appservice.PermissionRequired
import com.hedgehog.gdzietabiedra.appservice.ShopService
import com.hedgehog.gdzietabiedra.appservice.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(
    private val shopService: ShopService,
    private val locationWatchdogCoroutines: LocationWatchdogCoroutines,
) : ViewModel() {

  // TODO: 24.10.2020 Do I need this here?
  private val _permissionRequest = MutableLiveData<String>()
  val permissionRequest: LiveData<String> = _permissionRequest

  private val _shopList = MutableLiveData<List<Shop>>()
  val shopList: LiveData<List<Shop>> = _shopList

  init {
  }

  fun loadData() {
//    checkPosition()
  }

  private fun checkPosition() {
    viewModelScope.launch(Dispatchers.IO) {
      when (val position = locationWatchdogCoroutines.getPosition()) {
        is Success -> {
          viewModelScope.launch(Dispatchers.IO) {
            _shopList.postValue(shopService.getShopsInRange(position.position, 50_000.0))
          }
        }
        is Error -> println("no location available")
        PermissionRequired -> requestLocationPermission()
      }
    }
  }

  private fun requestLocationPermission() {
    _permissionRequest.postValue(Manifest.permission.ACCESS_FINE_LOCATION)
  }

  fun permissionGranted() {
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
}