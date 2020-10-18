package com.hedgehog.gdzietabiedra.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShopRoomDao {
  @Query("SELECT * FROM shop")
  fun getAll(): List<ShopRoomEntity>

  @Insert
  fun insertAll(vararg shops: ShopRoomEntity)

  @Delete
  fun delete(user: ShopRoomEntity)
}
