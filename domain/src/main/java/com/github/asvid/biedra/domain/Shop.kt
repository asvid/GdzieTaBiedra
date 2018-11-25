package com.github.asvid.biedra.domain

data class Shop(
    val id: String,
    val address: String,
    var distance: Double?,
    val location: Position,
    val openHours: String
)