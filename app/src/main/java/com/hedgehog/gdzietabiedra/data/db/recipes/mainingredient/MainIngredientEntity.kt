package com.hedgehog.gdzietabiedra.data.db.recipes.mainingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val TABLE_NAME = "main_ingredient"

@Entity(tableName = TABLE_NAME)
data class MainIngredientEntity(
        @PrimaryKey
        @ColumnInfo(name = ID)
        val id: Int,
        val name: String
) {
    companion object {
        const val ID = "main_ingredient_id"
    }
}
