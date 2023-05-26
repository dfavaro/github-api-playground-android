package com.danielefavaro.githubapiplayground.feature.repodetail.ui.model

import com.danielefavaro.githubapiplayground.core.model.UserRepoModel

data class RepoDetailUiState(
    val name: String,
    val isPrivate: Boolean,
    val owner: UserRepoModel.Owner,
    val url: String,
    val isArchived: Boolean,
    val watchers: Int,
    val stars: Int
)
