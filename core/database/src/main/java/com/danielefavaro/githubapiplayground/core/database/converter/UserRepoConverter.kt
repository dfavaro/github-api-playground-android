package com.danielefavaro.githubapiplayground.core.database.converter

import androidx.room.TypeConverter
import com.danielefavaro.githubapiplayground.core.database.model.UserRepoEntity
import com.google.gson.Gson

object UserRepoConverter {

    @TypeConverter
    fun fromOwner(value: UserRepoEntity.Owner): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toOwner(value: String): UserRepoEntity.Owner {
        return Gson().fromJson(value, UserRepoEntity.Owner::class.java)
    }
}
