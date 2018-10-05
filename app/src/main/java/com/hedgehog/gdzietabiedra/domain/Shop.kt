package com.hedgehog.gdzietabiedra.domain

data class Shop(
    val id: String,
    val address: String,
    var distance: Double?,
    val location: Point,
    val openHours: String
)