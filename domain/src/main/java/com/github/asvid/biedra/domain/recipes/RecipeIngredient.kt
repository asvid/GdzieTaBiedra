package com.github.asvid.biedra.domain.recipes

data class RecipeIngredient(
        val id: Int,
        val measureId: Int,
        val name: String,
        val amount: Int?,
        val productId: Int?,
        val measureName: String?
)