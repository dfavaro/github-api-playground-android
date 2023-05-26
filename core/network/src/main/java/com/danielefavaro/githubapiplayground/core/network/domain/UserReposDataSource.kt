package com.danielefavaro.githubapiplayground.core.network.domain

import com.danielefavaro.githubapiplayground.core.network.model.NetworkUserRepo

interface UserReposDataSource {
    suspend fun getUserRepos(): List<NetworkUserRepo>
}
