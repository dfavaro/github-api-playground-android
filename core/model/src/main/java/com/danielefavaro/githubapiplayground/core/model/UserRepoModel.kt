package com.danielefavaro.githubapiplayground.core.model

data class UserRepoModel(
    val id: Long,
    val name: String,
    val fullName: String,
    val isPrivate: Boolean,
    val owner: Owner,
    val description: String?,
    val url: String,
    val createdAt: String,
    val updatedAt: String,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String?,
    val archived: Boolean
) {
    data class Owner(
        val login: String,
        val avatarUrl: String,
        val url: String
    )
}
