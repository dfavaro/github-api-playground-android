package com.danielefavaro.githubapiplayground.core.network.di

import com.danielefavaro.githubapiplayground.core.network.UserReposDataSourceImpl
import com.danielefavaro.githubapiplayground.core.network.api.GithubService
import com.danielefavaro.githubapiplayground.core.network.domain.UserReposDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GithubNetworkModule {

    @Singleton
    @Provides
    fun provideGithubRemoteService(retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)

    @Singleton
    @Provides
    fun provideRemoteSource(
        dispatcher: CoroutineDispatcher,
        githubService: GithubService
    ): UserReposDataSource = UserReposDataSourceImpl(
        dispatcher = dispatcher,
        githubService = githubService,
    )
}
