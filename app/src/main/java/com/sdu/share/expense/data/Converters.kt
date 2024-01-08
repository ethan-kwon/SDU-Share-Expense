package com.sdu.share.expense.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun stringToList(value: String?): MutableList<String>? {
        return value?.let { value.split(",").map { it.trim() } }
                as MutableList<String>?
    }

    @TypeConverter
    fun listToString(value: MutableList<String>?): String? {
        return value?.joinToString()
    }

    @TypeConverter
    fun intStringToList(value: String?): MutableList<Int>? {
        return value?.let { value.split(",").map { it.trim().toInt() } }
                as MutableList<Int>?
    }

    @TypeConverter
    fun intListToString(value: MutableList<Int>?): String? {
        return value?.joinToString()
    }
}