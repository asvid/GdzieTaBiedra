package com.hedgehog.gdzietabiedra.data.db

import androidx.room.TypeConverter
import com.github.asvid.biedra.domain.recipes.DifficultyLevel
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun toDifficultyLevel(value: String) = enumValueOf<DifficultyLevel>(value)

    @TypeConverter
    fun fromDifficultyLevel(value: DifficultyLevel) = value.name

    @TypeConverter
    fun restoreList(listOfString: String): List<String> {
        return Json.decodeFromString(ListSerializer(String.serializer()), listOfString)
    }

    @TypeConverter
    fun saveList(listOfString: List<String>): String {
        return Json.encodeToString(ListSerializer(String.serializer()), listOfString)
    }
}

