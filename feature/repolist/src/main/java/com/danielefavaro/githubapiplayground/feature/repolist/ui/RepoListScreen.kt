package com.danielefavaro.githubapiplayground.feature.repolist.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.danielefavaro.githubapiplayground.core.ui.MyApplicationTheme
import com.danielefavaro.githubapiplayground.core.ui.component.BaseScaffold
import com.danielefavaro.githubapiplayground.core.ui.component.toolbar.ToolBar
import com.danielefavaro.githubapiplayground.feature.repolist.R
import com.danielefavaro.githubapiplayground.feature.repolist.ui.component.ListItem

@Composable
fun RepoListScreen(
    viewModel: RepoListViewModel = hiltViewModel(),
    onItemClick: (id: Long) -> Unit
) {
    val state by viewModel.state.collectAsState()

    BaseScaffold(
        viewModel = viewModel,
        onContentRefresh = {
            viewModel.fetchUserRepos()
        },
        onSnackbarResult = {
            when (it) {
                SnackbarResult.ActionPerformed -> viewModel.fetchUserRepos()
                else -> Unit
            }
        },
        topBar = {
            ToolBar(
                title = stringResource(id = R.string.user_repos_title)
            )
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = state.data) {
                ListItem(
                    title = it.name,
                    starsCount = it.stars,
                    onClick = {
                        onItemClick(it.id)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        RepoListScreen(
            onItemClick = {}
        )
    }
}
