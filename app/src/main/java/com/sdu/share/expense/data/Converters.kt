package com.sdu.share.expense.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun stringToList(value: String?): List<String>? {
        return value?.let { value.split(",").map { it.trim() } }
    }

    @TypeConverter
    fun listToString(value: List<String>?): String? {
        return value?.joinToString()
    }

    @TypeConverter
    fun intStringToList(value: String?): List<Int>? {
        return value?.let { value.split(",").map { it.trim().toInt() } }
    }

    @TypeConverter
    fun intListToString(value: List<Int>?): String? {
        return value?.joinToString()
    }
}