package com.danielefavaro.githubapiplayground.core.database.di

import android.content.Context
import androidx.room.Room
import com.danielefavaro.githubapiplayground.core.database.AppDatabase
import com.danielefavaro.githubapiplayground.core.database.dao.UserReposDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideUserReposDao(appDatabase: AppDatabase): UserReposDao {
        return appDatabase.userReposDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }
}
