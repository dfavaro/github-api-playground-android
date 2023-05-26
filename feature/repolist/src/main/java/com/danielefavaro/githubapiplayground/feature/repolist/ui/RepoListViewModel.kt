package com.danielefavaro.githubapiplayground.feature.repolist.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import com.danielefavaro.githubapiplayground.core.data.domain.UserReposRepository
import com.danielefavaro.githubapiplayground.core.model.exception.ConnectionError
import com.danielefavaro.githubapiplayground.core.ui.model.BaseUiEvent
import com.danielefavaro.githubapiplayground.core.ui.viewmodel.BaseViewModel
import com.danielefavaro.githubapiplayground.feature.repolist.R
import com.danielefavaro.githubapiplayground.feature.repolist.ui.model.RepoListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val userReposRepository: UserReposRepository
) : BaseViewModel() {

    val state: StateFlow<RepoListUiState> = MutableStateFlow(RepoListUiState())

    init {
        fetchUserRepos()
        collectUserRepos()
    }

    fun fetchUserRepos() {
        onLoadingState(isLoading = true)

        viewModelScope.launch {
            try {
                userReposRepository.updateUserRepos()
            } catch (e: Exception) {
                onLoadingState(isLoading = false)
                when (e) {
                    is ConnectionError -> onErrorEvent(
                        BaseUiEvent.ErrorEvent.OnErrorResWithRetry(R.string.connection_error)
                    )

                    else -> onErrorEvent(
                        BaseUiEvent.ErrorEvent.OnErrorRes(R.string.generic_error)
                    )
                }
            }
        }
    }

    @VisibleForTesting
    fun collectUserRepos() {
        viewModelScope.launch {
            try {
                userReposRepository.getUserRepos().collect {
                    it.map { userRepo ->
                        RepoListUiState.UserRepo(
                            id = userRepo.id,
                            name = userRepo.name,
                            isPrivate = userRepo.isPrivate,
                            stars = userRepo.stargazersCount
                        )
                    }.also { userRepos ->
                        (state as MutableStateFlow).update { state ->
                            state.copy(
                                data = userRepos
                            )
                        }
                    }.also {
                        onLoadingState(isLoading = false)
                    }
                }
            } catch (e: Exception) {
                onLoadingState(isLoading = false)
                // TODO check for different errors
                onErrorEvent(BaseUiEvent.ErrorEvent.OnErrorRes(R.string.generic_error))
            }
        }
    }
}
