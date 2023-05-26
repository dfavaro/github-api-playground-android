package com.danielefavaro.githubapiplayground.core.data.model

import com.danielefavaro.githubapiplayground.core.database.model.UserRepoEntity
import com.danielefavaro.githubapiplayground.core.model.UserRepoModel
import com.danielefavaro.githubapiplayground.core.network.model.NetworkUserRepo

fun NetworkUserRepo.asEntity() = UserRepoEntity(
    id = id,
    name = name,
    fullName = fullName,
    isPrivate = isPrivate,
    owner = owner.asEntity(),
    description = description,
    url = htmlUrl,
    createdAt = createdAt,
    updatedAt = updatedAt,
    stargazersCount = stargazersCount,
    watchersCount = watchersCount,
    language = language,
    archived = archived
)

private fun NetworkUserRepo.Owner.asEntity() = UserRepoEntity.Owner(
    login = login,
    avatarUrl = avatarUrl,
    url = url
)

fun UserRepoEntity.asBusiness() = UserRepoModel(
    id = id,
    name = name,
    fullName = fullName,
    isPrivate = isPrivate,
    owner = owner.asBusiness(),
    description = description,
    url = url,
    createdAt = createdAt,
    updatedAt = updatedAt,
    stargazersCount = stargazersCount,
    watchersCount = watchersCount,
    language = language,
    archived = archived
)

private fun UserRepoEntity.Owner.asBusiness() = UserRepoModel.Owner(
    login = login,
    avatarUrl = avatarUrl,
    url = url
)

