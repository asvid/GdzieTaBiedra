package com.hedgehog.gdzietabiedra.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopRoomDao {
  @Query("SELECT * FROM shoproomentity")
  fun getAll(): List<ShopRoomEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(vararg shop: ShopRoomEntity)

  @Delete
  fun delete(user: ShopRoomEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(it: ShopRoomEntity)
}
