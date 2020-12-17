package com.hedgehog.gdzietabiedra.data.db.recipes.ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.hedgehog.gdzietabiedra.data.db.recipes.RecipeEntity
import com.hedgehog.gdzietabiedra.data.db.recipes.ingredient.amount.AmountEntity

@Entity(tableName = "ingredientAndAmount", foreignKeys = [
    (ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = [(RecipeEntity.ID)],
            childColumns = [(IngredientAndAmountEntity.RECIPE_ID)],
            onDelete = CASCADE,
            onUpdate = CASCADE
    ))])
data class IngredientAndAmountEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = RECIPE_ID)
        val recipeId: Int,
        val ingredientEntity: IngredientEntity,
        val amountEntity: AmountEntity
) {
    companion object {
        const val ID = "ingredient_and_amount_id"
        const val RECIPE_ID = "recipe_id"
    }
}
