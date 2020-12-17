package com.hedgehog.gdzietabiedra.data.db.recipes

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.RESTRICT
import com.github.asvid.biedra.domain.recipes.*
import com.hedgehog.gdzietabiedra.data.db.recipes.cuisine.CuisineEntity
import com.hedgehog.gdzietabiedra.data.db.recipes.ingredient.IngredientAndAmountEntity
import com.hedgehog.gdzietabiedra.data.db.recipes.mainingredient.MainIngredientEntity

internal const val TABLE_NAME = "recipes"
const val ID = "recipe_id"
internal const val CUISINE_ID = "cuisine_id"
internal const val MAIN_INGREDIENT_ID = "main_ingredient_id"

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

        @Embedded
        val mainIngredient: MainIngredient?,

        @Relation(
                parentColumn = ID,
                entityColumn = IngredientAndAmountEntity.RECIPE_ID,
                entity = IngredientAndAmountEntity::class)
        val ingredients: List<IngredientAndAmountEntity>,
        val recipeType: RecipeType?, // simple ENUM

        @ColumnInfo(name = CUISINE_ID)
        val cuisineId: Int?,
        val metaTitle: String?,
        val metaDescription: String?,
        val images: List<String>,
) {
    companion object {
        const val ID = "recipe_id"
    }
}