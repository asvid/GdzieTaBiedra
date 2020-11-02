package com.hedgehog.gdzietabiedra.ui.sundays

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SundaysViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sundays Fragment"
    }
    val text: LiveData<String> = _text
}