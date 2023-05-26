package com.danielefavaro.githubapiplayground.core.navigation.route

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danielefavaro.githubapiplayground.core.navigation.BaseDestination
import com.danielefavaro.githubapiplayground.core.navigation.toPathWithParameter
import com.danielefavaro.githubapiplayground.core.navigation.toRoute

private const val REPO_DETAIL_ROUTE = "repoDetail"

object RepoDetailDestination : BaseDestination() {
    const val idArg = "id"

    override val route: String = toRoute(REPO_DETAIL_ROUTE)
    override val params: String
        get() = toPathWithParameter(idArg)

    val arguments = listOf(
        navArgument(idArg) { type = NavType.LongType }
    )
}
