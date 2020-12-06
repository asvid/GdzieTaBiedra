package com.github.asvid.biedra.domain.recipes

data class Ingredient(
        val id: Int,
        val measure: Measure,
        val name: String,
        val amount: Int?,
        val productId: Int?,
)