package com.danielefavaro.githubapiplayground.core.data.domain

import com.danielefavaro.githubapiplayground.core.model.UserRepoModel
import kotlinx.coroutines.flow.Flow

interface UserReposRepository {
    suspend fun getUserRepos(): Flow<List<UserRepoModel>>
    suspend fun updateUserRepos()
    suspend fun getUserRepo(id: Long): UserRepoModel
}
