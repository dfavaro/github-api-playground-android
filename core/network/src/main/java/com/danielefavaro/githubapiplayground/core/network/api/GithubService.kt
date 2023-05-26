package com.danielefavaro.githubapiplayground.core.network.api

import com.danielefavaro.githubapiplayground.core.network.model.NetworkUserRepo
import retrofit2.http.GET
import retrofit2.http.Headers

interface GithubService {
    @GET("user/repos")
    @Headers("Accept: application/vnd.github+json")
    suspend fun getUserRepos(): List<NetworkUserRepo>
}
