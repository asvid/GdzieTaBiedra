package com.github.asvid.biedra.domain.recipes

data class Recipe(
        val id: Int,
        val name: String,
        val description: String, // html...
        val descriptionTip: String?,
        val portions: Int?,
        val difficultyLevel: DifficultyLevel?,
        val mainIngredient: MainIngredient?,
        val ingredients: List<Ingredient>,
        val recipeType: RecipeType?, //  ["drugie danie: ID=6"]
        val cuisine: Cuisine?,
        val metaTitle: String?,
        val metaDescription: String?,
        val images: List<RecipeImage>,
)