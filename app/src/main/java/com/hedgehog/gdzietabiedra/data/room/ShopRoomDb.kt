package com.hedgehog.gdzietabiedra.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShopRoomEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun shopRoomDao(): ShopRoomDao
}
