package com.danielefavaro.githubapiplayground.feature.repodetail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.danielefavaro.githubapiplayground.core.ui.MyApplicationTheme
import com.danielefavaro.githubapiplayground.core.ui.component.toolbar.ToolBar
import com.danielefavaro.githubapiplayground.core.ui.component.toolbar.ToolbarActionModel
import com.danielefavaro.githubapiplayground.core.ui.defaultMargin
import com.danielefavaro.githubapiplayground.feature.repodetail.R
import com.danielefavaro.githubapiplayground.feature.repodetail.ui.component.Body
import com.danielefavaro.githubapiplayground.feature.repodetail.ui.component.Footer
import com.danielefavaro.githubapiplayground.feature.repodetail.ui.component.Header

@Composable
internal fun RepoDetailScreen(
    viewModel: RepoDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            ToolBar(
                title = state?.name.orEmpty(),
                navActionModel = ToolbarActionModel.Back(
                    caption = stringResource(id = R.string.simple_back_caption),
                    onClick = onBackClick
                )
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(defaultMargin),
        ) {
            Header(
                avatarUrl = state?.owner?.avatarUrl,
                ownerName = state?.owner?.login.orEmpty(),
                isPrivate = state?.isPrivate == true,
                isArchived = state?.isArchived == true
            )

            Body(
                watchers = state?.watchers ?: 0,
                stars = state?.stars ?: 0
            )

            Spacer(modifier = Modifier.weight(1f))

            state?.url?.let { url ->
                Footer(
                    ctaUrl = url
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        RepoDetailScreen(
            onBackClick = {}
        )
    }
}
