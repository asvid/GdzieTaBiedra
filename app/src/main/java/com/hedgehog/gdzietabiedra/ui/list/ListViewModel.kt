package com.hedgehog.gdzietabiedra.ui.list

import android.Manifest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hedgehog.gdzietabiedra.appservice.Error
import com.hedgehog.gdzietabiedra.appservice.LocationWatchdogCoroutines
import com.hedgehog.gdzietabiedra.appservice.PermissionRequired
import com.hedgehog.gdzietabiedra.appservice.Success
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import kotlinx.coroutines.launch

class ListViewModel(
    private val shopsRepository: ShopsRepository,
    private val locationWatchdogCoroutines: LocationWatchdogCoroutines,
) : ViewModel() {

  private val _permissionRequest = MutableLiveData<String>()
  private val _text = MutableLiveData<String>().apply {
    value = "This is list Fragment"
  }
  val text: LiveData<String> = _text
  val permissionRequest: LiveData<String> = _permissionRequest

  init {
  }

  fun loadData() {
//    locationWatchdog.getLocation()
//        .map {
//          println("position: $it")
//        }
//        .subscribe()

    checkPosition()

//    viewModelScope.launch(Dispatchers.IO) {
//      val shops = shopsRepository.fetchAll()
//
//      _text.postValue("we have ${shops.size} shops in DB")
//    }
  }

  private fun checkPosition() {
    viewModelScope.launch {
      when (val position = locationWatchdogCoroutines.getPosition()) {
        is Success -> _text.postValue("position: $position")
        is Error -> _text.postValue("oh no, location error...")
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
}