package com.danielefavaro.githubapiplayground.feature.repodetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.danielefavaro.githubapiplayground.core.data.domain.UserReposRepository
import com.danielefavaro.githubapiplayground.core.model.UserRepoModel
import com.danielefavaro.githubapiplayground.core.navigation.route.RepoDetailDestination
import com.danielefavaro.githubapiplayground.core.ui.viewmodel.BaseViewModel
import com.danielefavaro.githubapiplayground.feature.repodetail.ui.model.RepoDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RepoDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userReposRepository: UserReposRepository
) : BaseViewModel() {

    private val repoId: Long = checkNotNull(savedStateHandle[RepoDetailDestination.idArg])

    val state: StateFlow<RepoDetailUiState?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            val userRepo: UserRepoModel = userReposRepository.getUserRepo(id = repoId)

            (state as MutableStateFlow).update {
                RepoDetailUiState(
                    name = userRepo.name,
                    isPrivate = userRepo.isPrivate,
                    owner = userRepo.owner,
                    url = userRepo.url,
                    isArchived = userRepo.archived,
                    watchers = userRepo.watchersCount,
                    stars = userRepo.stargazersCount
                )
            }
        }
    }
}
