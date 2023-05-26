package com.danielefavaro.githubapiplayground.feature.repolist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.danielefavaro.githubapiplayground.core.navigation.route.RepoDetailDestination
import com.danielefavaro.githubapiplayground.core.navigation.route.RepoListDestination
import com.danielefavaro.githubapiplayground.feature.repolist.ui.RepoListScreen

fun NavGraphBuilder.repoListGraph(
    navController: NavHostController
) {
    composable(RepoListDestination.route) {
        RepoListScreen(
            onItemClick = { id ->
                navController.navigate("${RepoDetailDestination.route}/$id")
            }
        )
    }
}
