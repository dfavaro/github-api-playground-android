package com.danielefavaro.githubapiplayground.core.network

import com.danielefavaro.githubapiplayground.core.network.api.GithubService
import com.danielefavaro.githubapiplayground.core.network.domain.UserReposDataSource
import com.danielefavaro.githubapiplayground.core.network.helper.safeApiCall
import com.danielefavaro.githubapiplayground.core.network.model.NetworkUserRepo
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class UserReposDataSourceImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val githubService: GithubService
) : UserReposDataSource {

    override suspend fun getUserRepos(): List<NetworkUserRepo> =
        safeApiCall(dispatcher = dispatcher) {
            githubService.getUserRepos()
        }
}
