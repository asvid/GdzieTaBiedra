package com.hedgehog.gdzietabiedra.data.db.recipes.ingredient

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.asvid.biedra.domain.recipes.Measure
import com.hedgehog.gdzietabiedra.data.db.recipes.ingredient.amount.AmountEntity

internal const val TABLE_NAME = "ingredients"

@Entity(tableName = TABLE_NAME)
data class IngredientEntity(
        @PrimaryKey
        @ColumnInfo(name = ID)
        val id: Int,
        val name: String,
        val productId: Int?,
) {
    companion object {
        const val ID = "ingredient_id"
    }
}
