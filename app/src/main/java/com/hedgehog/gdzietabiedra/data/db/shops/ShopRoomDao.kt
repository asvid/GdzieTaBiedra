package com.hedgehog.gdzietabiedra.data.db.shops

import androidx.room.*

@Dao
interface ShopRoomDao {
    @Query("SELECT * FROM shoproomentity")
    suspend fun getAll(): List<ShopRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg shop: ShopRoomEntity)

    @Delete
    suspend fun delete(user: ShopRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(it: ShopRoomEntity)

    @Query("SELECT * FROM shoproomentity WHERE id=:id")
    suspend fun getById(id: String): ShopRoomEntity?

    @Query(
            "SELECT * FROM shoproomentity WHERE latitude BETWEEN :minLat AND :maxLat AND longitude BETWEEN :minLng AND :maxLng")
    suspend fun fetchInRange(minLat: Double, maxLat: Double, minLng: Double,
                             maxLng: Double): List<ShopRoomEntity>

    @Query(
            "SELECT * FROM shoproomentity WHERE " +
                    "street LIKE '%'||:address||'%' OR " +
                    "city LIKE '%'||:address||'%' OR " +
                    "citySlug LIKE '%'||:address||'%' OR " +
                    "name LIKE '%'||:address||'%' " +
                    "")
    suspend fun fetchForAddress(address: String): List<ShopRoomEntity>
}
