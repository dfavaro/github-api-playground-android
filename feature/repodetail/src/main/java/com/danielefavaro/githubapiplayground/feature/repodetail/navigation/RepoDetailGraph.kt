package com.danielefavaro.githubapiplayground.feature.repodetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.danielefavaro.githubapiplayground.core.navigation.route.RepoDetailDestination
import com.danielefavaro.githubapiplayground.feature.repodetail.ui.RepoDetailScreen

fun NavGraphBuilder.repoDetailGraph(
    navController: NavHostController
) {
    composable(
        route = RepoDetailDestination.routeParams,
        arguments = RepoDetailDestination.arguments
    ) {
        RepoDetailScreen(
            onBackClick = navController::popBackStack
        )
    }
}
