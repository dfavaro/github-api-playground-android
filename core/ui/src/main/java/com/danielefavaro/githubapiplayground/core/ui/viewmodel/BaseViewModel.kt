package com.danielefavaro.githubapiplayground.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielefavaro.githubapiplayground.core.ui.model.BaseUiEvent
import com.danielefavaro.githubplayground.core.common.domain.NetworkConnectivityService
import com.danielefavaro.githubplayground.core.common.model.NetworkStatusModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    networkConnectivityService: NetworkConnectivityService? = null
) : ViewModel() {

    val loadingState: StateFlow<Boolean> = MutableStateFlow(false)

    val refreshEvent: SharedFlow<BaseUiEvent.OnRefresh> = MutableSharedFlow()

    // Can even create a custom model for handling different data, such as a Retry callback op
    val errorEvent: SharedFlow<BaseUiEvent.ErrorEvent> = MutableSharedFlow()

    val networkStatus: StateFlow<NetworkStatusModel> =
        networkConnectivityService?.networkStatus?.stateIn(
            initialValue = NetworkStatusModel.Unknown,
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000)
        ) ?: MutableStateFlow(NetworkStatusModel.Unknown)

    fun onLoadingState(isLoading: Boolean) {
        (loadingState as MutableStateFlow).update {
            isLoading
        }
    }

    fun onErrorEvent(data: BaseUiEvent.ErrorEvent) {
        viewModelScope.launch {
            (errorEvent as MutableSharedFlow).emit(data)
        }
    }

    fun onRefreshEvent(isRefreshing: Boolean) {
        viewModelScope.launch {
            (refreshEvent as MutableSharedFlow).emit(BaseUiEvent.OnRefresh(isRefreshing = isRefreshing))
        }
    }
}
