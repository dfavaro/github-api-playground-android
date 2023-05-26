package com.danielefavaro.githubapiplayground.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.danielefavaro.githubapiplayground.core.ui.R
import com.danielefavaro.githubapiplayground.core.ui.model.BaseUiEvent
import com.danielefavaro.githubapiplayground.core.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Base Scaffold that should be used as the root component for every screen that needs to handle
 * errors or loading states.
 *
 * @param viewModel the injected viewModel into the composable screen
 */
@Composable
fun BaseScaffold(
    viewModel: BaseViewModel,
    onSnackbarResult: ((SnackbarResult) -> Unit)? = null,
    onContentRefresh: (() -> Unit)? = null,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    val loadingState by viewModel.loadingState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    val isRefreshing by produceState(initialValue = false, producer = {
        viewModel.refreshEvent.collect { event ->
            value = event.isRefreshing
        }
    })

    LaunchedEffect(key1 = Unit, block = {
        launch {
            viewModel.errorEvent.collect { event ->
                when (event) {
                    is BaseUiEvent.ErrorEvent.OnErrorRes -> snackbarHostState.showSnackbar(
                        message = context.getString(event.data)
                    )

                    is BaseUiEvent.ErrorEvent.OnErrorResWithRetry -> {
                        val result = snackbarHostState.showSnackbar(
                            message = context.getString(event.data),
                            actionLabel = context.getString(R.string.retry_action_label),
                            duration = SnackbarDuration.Indefinite
                        )

                        onSnackbarResult?.invoke(result)
                    }
                }
            }
        }
    })

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = topBar
    ) {
        Box(modifier = Modifier.padding(it)) {
            onContentRefresh?.let { onRefresh ->
                PullRefreshPlaceholder(
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh,
                    content = {
                        InnerContent(
                            isLoading = loadingState,
                            content = content
                        )
                    }
                )
            } ?: InnerContent(
                isLoading = loadingState,
                content = content
            )
        }
    }
}

@Composable
private fun InnerContent(
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    Column {
        if (isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        content.invoke()
    }
}
