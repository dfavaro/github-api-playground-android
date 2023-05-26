package com.danielefavaro.githubapiplayground.core.network

import com.danielefavaro.githubapiplayground.core.network.api.GithubService
import com.danielefavaro.githubapiplayground.core.network.domain.UserReposDataSource
import com.danielefavaro.githubapiplayground.core.testing.data.userReposData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserReposDataSourceTest {

    private lateinit var source: UserReposDataSource

    private val testDispatcher = StandardTestDispatcher()

    private val githubService: GithubService = mockk {
        coEvery {
            getUserRepos()
        } returns userReposData
    }

    @Before
    fun setUp() {
        source = UserReposDataSourceImpl(
            dispatcher = testDispatcher,
            githubService = githubService
        )
    }

    @Test
    fun testRemoteSourceReturnsExpectedData() = runTest(testDispatcher) {
        Assert.assertEquals(
            userReposData,
            source.getUserRepos()
        )
    }
}
