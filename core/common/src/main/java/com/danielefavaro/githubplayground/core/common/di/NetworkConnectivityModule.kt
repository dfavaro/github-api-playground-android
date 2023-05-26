package com.danielefavaro.githubplayground.core.common.di

import android.content.Context
import com.danielefavaro.githubplayground.core.common.NetworkConnectivityServiceImpl
import com.danielefavaro.githubplayground.core.common.domain.NetworkConnectivityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object NetworkConnectivityModule {

    @Singleton
    @Provides
    fun provideNetworkConnectivityService(
        @ApplicationContext context: Context,
        dispatcher: CoroutineDispatcher
    ): NetworkConnectivityService =
        NetworkConnectivityServiceImpl(
            context = context,
            dispatcher = dispatcher
        )
}
