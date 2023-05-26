package com.danielefavaro.githubapiplayground.core.ui.model

import androidx.annotation.StringRes

sealed class BaseUiEvent {
    sealed class ErrorEvent {
        data class OnErrorRes(@StringRes val data: Int) : ErrorEvent()
        data class OnErrorResWithRetry(@StringRes val data: Int) : ErrorEvent()
    }

    data class OnRefresh(val isRefreshing: Boolean)
}
