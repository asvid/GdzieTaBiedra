package com.hedgehog.gdzietabiedra.domain

import com.github.asvid.biedra.domain.Position

data class Shop(
    val id: String,
    val address: String,
    var distance: Double?,
    val location: Position,
    val openHours: String
)