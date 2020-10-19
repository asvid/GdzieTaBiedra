package com.hedgehog.gdzietabiedra.data.repository.shops

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShopRoomEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun shopRoomDao(): ShopRoomDao
}
