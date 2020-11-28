package com.hedgehog.gdzietabiedra.ui.list.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.asvid.biedra.domain.Shop
import com.hedgehog.gdzietabiedra.appservice.ShopService
import kotlinx.coroutines.launch

class ShopDetailsViewModel(
        private val shopService: ShopService
) : ViewModel() {

    private val _shopData = MutableLiveData<Shop>()
    val shopData: LiveData<Shop> = _shopData

    fun openedFromList(shopId: String) {
        viewModelScope.launch {
            _shopData.postValue(shopService.getShopById(shopId))
        }
    }
}