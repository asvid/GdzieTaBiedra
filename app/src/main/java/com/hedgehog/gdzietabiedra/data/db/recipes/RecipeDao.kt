package com.hedgehog.gdzietabiedra.data.db.recipes

import androidx.room.*

@Dao
interface RecipeDao {
    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAll(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg recipe: RecipeEntity)

    @Delete
    suspend fun delete(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(it: RecipeEntity)

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID=:id")
    suspend fun getById(id: String): RecipeEntity?
}