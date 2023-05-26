package com.danielefavaro.githubapiplayground.feature.repodetail.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.danielefavaro.githubapiplayground.core.data.domain.UserReposRepository
import com.danielefavaro.githubapiplayground.core.data.model.asBusiness
import com.danielefavaro.githubapiplayground.core.data.model.asEntity
import com.danielefavaro.githubapiplayground.core.navigation.route.RepoDetailDestination
import com.danielefavaro.githubapiplayground.core.testing.data.userReposData
import com.danielefavaro.githubapiplayground.core.testing.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RepoDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repoId = 1L
    private val savedStateHandle = SavedStateHandle(
        mapOf(
            RepoDetailDestination.idArg to repoId
        )
    )

    private lateinit var viewModel: RepoDetailViewModel
    private lateinit var userReposRepository: UserReposRepository


    @Before
    fun setup() {
        userReposRepository = mockk {
            coEvery { getUserRepo(id = repoId) } returns userReposData.find { it.id == repoId }!!
                .asEntity().asBusiness()
        }
        viewModel = RepoDetailViewModel(savedStateHandle, userReposRepository)
    }

    @Test
    fun testUserRepoFetchedFromNavArgRepoId() = runTest {
        viewModel.state.test {
            val item = awaitItem()

            assert(item?.name == userReposData[1].name)
            assert(item?.name != userReposData[0].name)
        }

        coVerify { userReposRepository.getUserRepo(id = repoId) }
    }
}
