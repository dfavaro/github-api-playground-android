package com.danielefavaro.githubapiplayground.feature.repolist.ui.model

data class RepoListUiState(
    val data: List<UserRepo> = emptyList()
) {
    data class UserRepo(
        val id: Long,
        val name: String,
        val isPrivate: Boolean,
        val stars: Int
    )
}
