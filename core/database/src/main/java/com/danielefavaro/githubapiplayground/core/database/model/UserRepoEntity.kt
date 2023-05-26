package com.danielefavaro.githubapiplayground.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_repository")
data class UserRepoEntity(
    @PrimaryKey val id: Long,
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
