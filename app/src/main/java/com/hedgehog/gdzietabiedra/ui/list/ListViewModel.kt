package com.hedgehog.gdzietabiedra.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hedgehog.gdzietabiedra.appservice.LocationWatchdog
import com.hedgehog.gdzietabiedra.data.repository.shops.ShopsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(
    private val shopsRepository: ShopsRepository,
    private val locationWatchdog: LocationWatchdog
) : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is list Fragment"
  }
  val text: LiveData<String> = _text

  init{
  }

  fun loadData() {
    locationWatchdog.getLocation()
        .map {
          println("position: $it")
        }
        .subscribe()

    viewModelScope.launch(Dispatchers.IO) {
      val shops = shopsRepository.fetchAll()

      _text.postValue("we have ${shops.size} shops in DB")
    }
  }
}