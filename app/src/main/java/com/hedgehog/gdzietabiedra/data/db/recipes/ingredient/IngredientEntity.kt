package com.hedgehog.gdzietabiedra.data.db.recipes.ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.asvid.biedra.domain.recipes.Measure

internal const val TABLE_NAME = "ingredients"

@Entity(tableName = TABLE_NAME)
data class IngredientEntity(
        @PrimaryKey
        @ColumnInfo(name = ID)
        val id: Int,
        val measure: Measure, // simple enum
        val name: String,
        val amount: Int?, // how to keep this in Recipe but not in Ingredient?
        val productId: Int?,
) {
    companion object {
        const val ID = "ingredient_id"
    }
}
