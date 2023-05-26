package com.danielefavaro.githubapiplayground.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danielefavaro.githubapiplayground.core.database.converter.BaseConverter
import com.danielefavaro.githubapiplayground.core.database.converter.UserRepoConverter
import com.danielefavaro.githubapiplayground.core.database.dao.UserReposDao
import com.danielefavaro.githubapiplayground.core.database.model.UserRepoEntity

@Database(
    entities = [
        UserRepoEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    BaseConverter::class,
    UserRepoConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userReposDao(): UserReposDao
}
