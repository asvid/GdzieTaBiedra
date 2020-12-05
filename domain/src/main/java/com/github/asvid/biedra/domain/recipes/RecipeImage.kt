package com.github.asvid.biedra.domain.recipes

data class RecipeImage(
        val id: Int,
        val recipeId: Int,
        val filename: String
)