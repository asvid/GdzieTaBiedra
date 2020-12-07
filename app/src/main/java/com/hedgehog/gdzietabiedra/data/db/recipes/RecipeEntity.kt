package com.hedgehog.gdzietabiedra.data.db.recipes

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.RESTRICT
import com.github.asvid.biedra.domain.recipes.*
import com.hedgehog.gdzietabiedra.data.db.recipes.cuisine.CuisineEntity
import com.hedgehog.gdzietabiedra.data.db.recipes.ingredient.IngredientEntity
import com.hedgehog.gdzietabiedra.data.db.recipes.mainingredient.MainIngredientEntity

internal const val TABLE_NAME = "recipes"
const val ID = "recipe_id"
internal const val CUISINE_ID = "cuisine_id"
internal const val MAIN_INGREDIENT_ID = "main_ingredient_id"
internal const val INGREDIENTS = "ingredients"

@Entity(tableName = TABLE_NAME,
        foreignKeys = [
            (ForeignKey(
                    entity = MainIngredientEntity::class,
                    parentColumns = [(MainIngredientEntity.ID)],
                    childColumns = [(MAIN_INGREDIENT_ID)],
                    onDelete = RESTRICT,
                    onUpdate = CASCADE
            )),
            (ForeignKey(
                    entity = CuisineEntity::class,
                    parentColumns = [(CuisineEntity.ID)],
                    childColumns = [(CUISINE_ID)],
                    onDelete = RESTRICT,
                    onUpdate = CASCADE
            ))
        ]
)
data class RecipeEntity(
        @PrimaryKey
        @ColumnInfo(name = ID)
        val id: Int,
        val name: String,
        val description: String,
        val descriptionTip: String?,
        val portions: Int?,
        val difficultyLevel: DifficultyLevel?, // simple ENUM

        @ColumnInfo(name = MAIN_INGREDIENT_ID)
        val mainIngredient: MainIngredient?,

        @Relation(
                parentColumn = INGREDIENTS,
                entityColumn = IngredientEntity.ID,
                entity = IngredientEntity::class)
        val ingredients: List<Ingredient>,
        val recipeType: RecipeType?, // simple ENUM

        @ColumnInfo(name = CUISINE_ID)
        val cuisineId: Int?,
        val metaTitle: String?,
        val metaDescription: String?,
        val images: List<RecipeImage>,
)