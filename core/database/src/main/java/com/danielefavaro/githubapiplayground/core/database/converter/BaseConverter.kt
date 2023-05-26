package com.danielefavaro.githubapiplayground.core.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BaseConverter {
    @TypeConverter
    fun toString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(string: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(string, listType)
    }
}
