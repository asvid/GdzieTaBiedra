package com.github.asvid.biedra.domain.recipes

data class Recipe(
        val id: Int,
        val mediaId: Long?,
        val name: String,
        val description: String, // html...
        val descriptionTip: String?,
        val portions: Int?,
        val difficultyLevel: DifficultyLevel, // domain object?
        val category: String?, // domain object? [Array, 20, prl]
        val mainIngredient: MainIngredient?,
        val recipeType: RecipeType?, //  ["drugie danie"]
        val cuisineId: Int?,
        val cuisineName: String?,
        val groupId: Int?,
        val type: Int?, //WTF
        val metaTitle: String?,
        val metaDescription: String?,
        val images: List<RecipeImage>,
        val ingredients: List<RecipeIngredient>,
)