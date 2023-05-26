package com.danielefavaro.githubapiplayground.core.navigation.route

import com.danielefavaro.githubapiplayground.core.navigation.BaseDestination
import com.danielefavaro.githubapiplayground.core.navigation.toRoute

private const val REPO_LIST_ROUTE = "repoList"

object RepoListDestination : BaseDestination() {
    override val route: String = toRoute(REPO_LIST_ROUTE)
}
