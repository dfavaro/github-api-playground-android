package com.danielefavaro.githubapiplayground.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.danielefavaro.githubapiplayground.core.navigation.route.RepoListDestination
import com.danielefavaro.githubapiplayground.feature.repodetail.navigation.repoDetailGraph
import com.danielefavaro.githubapiplayground.feature.repolist.navigation.repoListGraph

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RepoListDestination.route
    ) {
        repoListGraph(navController = navController)
        repoDetailGraph(navController = navController)
    }
}
