package com.hedgehog.gdzietabiedra.data.db.recipes.ingredient.amount

import androidx.room.Entity
import com.github.asvid.biedra.domain.recipes.Measure

private const val TABLE_NAME = "ingredient_amount"

@Entity(tableName = TABLE_NAME)
data class AmountEntity(
        val id: Int,
        val amount: Int,
        val measure: Measure, // simple enum
)