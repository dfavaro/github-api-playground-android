package com.danielefavaro.githubapiplayground.feature.repolist.ui

import app.cash.turbine.test
import com.danielefavaro.githubapiplayground.core.data.domain.UserReposRepository
import com.danielefavaro.githubapiplayground.core.data.model.asBusiness
import com.danielefavaro.githubapiplayground.core.data.model.asEntity
import com.danielefavaro.githubapiplayground.core.model.exception.ConnectionError
import com.danielefavaro.githubapiplayground.core.testing.data.userReposData
import com.danielefavaro.githubapiplayground.core.testing.utils.MainDispatcherRule
import com.danielefavaro.githubapiplayground.core.ui.model.BaseUiEvent
import com.danielefavaro.githubapiplayground.feature.repolist.ui.model.RepoListUiState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepoListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repoId = 1L

    private lateinit var spiedViewModel: RepoListViewModel

    private val userReposRepository: UserReposRepository = mockk {
        coEvery { getUserRepos() } returns flowOf(
            userReposData.map {
                it.asEntity().asBusiness()
            }
        )
        coEvery { getUserRepo(id = repoId) } returns userReposData.find { it.id == repoId }!!
            .asEntity().asBusiness()
    }

    @Before
    fun setup() {
        val viewModel = RepoListViewModel(
            userReposRepository = userReposRepository
        )
        spiedViewModel = spyk(viewModel)
    }

    @Test
    fun testFetchUserReposSuccess() = runTest {
        spiedViewModel.fetchUserRepos()

        coVerify { userReposRepository.updateUserRepos() }
        verify { spiedViewModel.onLoadingState(isLoading = false) }
    }

    @Test
    fun testFetchUserReposError() = runTest(UnconfinedTestDispatcher()) {
        coEvery { userReposRepository.updateUserRepos() } throws ConnectionError()

        val job = launch {
            spiedViewModel.errorEvent.test {
                val item = awaitItem()

                assert(item is BaseUiEvent.ErrorEvent.OnErrorResWithRetry)
                cancelAndConsumeRemainingEvents()
            }
        }

        spiedViewModel.fetchUserRepos()

        coVerify { userReposRepository.updateUserRepos() }
        verify { spiedViewModel.onLoadingState(isLoading = false) }
        verify {
            spiedViewModel.onErrorEvent(any<BaseUiEvent.ErrorEvent.OnErrorResWithRetry>())
        }

        job.apply {
            join()
            cancel()
        }
    }

    @Test
    fun testCollectUserReposSuccess() = runTest {
        val expectedState = RepoListUiState(data = userReposData.map { userRepo ->
            RepoListUiState.UserRepo(
                id = userRepo.id,
                name = userRepo.name,
                isPrivate = userRepo.isPrivate,
                stars = userRepo.stargazersCount
            )
        })

        spiedViewModel.collectUserRepos()
        verify { spiedViewModel.onLoadingState(isLoading = false) }

        spiedViewModel.state.test {
            val item = awaitItem()
            assert(item == expectedState)
        }
    }

    @Test
    fun testCollectUserReposError() = runTest(UnconfinedTestDispatcher()) {
        coEvery { userReposRepository.getUserRepos() } throws ConnectionError()

        val job = launch {
            spiedViewModel.errorEvent.test {
                val item = awaitItem()

                assert(item is BaseUiEvent.ErrorEvent.OnErrorRes)
                cancelAndConsumeRemainingEvents()
            }
        }

        spiedViewModel.collectUserRepos()

        coVerify { userReposRepository.updateUserRepos() }
        verify { spiedViewModel.onLoadingState(false) }
        verify {
            spiedViewModel.onErrorEvent(any<BaseUiEvent.ErrorEvent.OnErrorRes>())
        }

        job.apply {
            join()
            cancel()
        }
    }
}
