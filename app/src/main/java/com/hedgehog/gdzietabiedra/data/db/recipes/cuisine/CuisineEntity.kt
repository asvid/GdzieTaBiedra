@file:JvmName("CuisineDaoKt")

package com.hedgehog.gdzietabiedra.data.db.recipes.cuisine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val TABLE_NAME = "cuisine"

@Entity(tableName = com.hedgehog.gdzietabiedra.data.db.recipes.difficulty.TABLE_NAME)
data class CuisineEntity(
        @PrimaryKey
        @ColumnInfo(name = ID)
        val id: Int,
        val name: String
){
        companion object{
                const val ID = "cuisine_id"
        }
}
