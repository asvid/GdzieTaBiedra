package com.hedgehog.gdzietabiedra.data.db.recipes.cuisine

import androidx.room.*
import com.hedgehog.gdzietabiedra.data.db.recipes.difficulty.MainIngredientEntity

@Dao
interface CuisineDao {
    @Query("SELECT * FROM ${com.hedgehog.gdzietabiedra.data.db.recipes.difficulty.TABLE_NAME}")
    suspend fun getAll(): List<MainIngredientEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg recipe: MainIngredientEntity)

    @Delete
    suspend fun delete(recipe: MainIngredientEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(it: MainIngredientEntity)

    @Query("SELECT * FROM ${com.hedgehog.gdzietabiedra.data.db.recipes.difficulty.TABLE_NAME} WHERE $ID=:id")
    suspend fun getById(id: String): MainIngredientEntity?
}