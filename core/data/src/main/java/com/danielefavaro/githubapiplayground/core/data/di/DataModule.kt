package com.danielefavaro.githubapiplayground.core.data.di

import com.danielefavaro.githubapiplayground.core.data.UserReposRepositoryImpl
import com.danielefavaro.githubapiplayground.core.data.domain.UserReposRepository
import com.danielefavaro.githubapiplayground.core.database.dao.UserReposDao
import com.danielefavaro.githubapiplayground.core.network.domain.UserReposDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideUserReposRepository(
        userReposDao: UserReposDao,
        userReposDataSource: UserReposDataSource
    ): UserReposRepository = UserReposRepositoryImpl(
        userReposDao = userReposDao,
        userReposDataSource = userReposDataSource
    )
}
