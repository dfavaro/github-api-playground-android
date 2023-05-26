package com.danielefavaro.githubapiplayground.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.danielefavaro.githubapiplayground.core.data.model.asEntity
import com.danielefavaro.githubapiplayground.core.database.dao.UserReposDao
import com.danielefavaro.githubapiplayground.core.testing.data.userReposData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class AppDatabaseTest {

    private lateinit var userReposDao: UserReposDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()
        userReposDao = appDatabase.userReposDao()
    }

    @Test
    fun testCache() = runTest {
        userReposDao.insertUserRepos(
            items = userReposData.map {
                it.asEntity()
            }
        )

        userReposDao.getUserRepos().test {
            val list = awaitItem()

            Assert.assertEquals(
                list.size,
                userReposData.size
            )

            Assert.assertEquals(
                list.first().name,
                userReposData.first().name
            )

            Assert.assertEquals(
                list.last().name,
                userReposData.last().name
            )
        }
    }

    @Test
    fun testCacheUpdate() = runTest {
        val droppedItems = 1
        userReposDao.insertUserRepos(
            items = userReposData
                .toMutableList()
                .drop(droppedItems)
                .map {
                    it.asEntity()
                }
        )

        userReposDao.getUserRepos().test {
            val list = awaitItem()

            Assert.assertTrue(
                list.size == userReposData.size - droppedItems
            )

            Assert.assertTrue(
                list.first().name != userReposData.first().name
            )

            Assert.assertTrue(
                list.last().name == userReposData.last().name
            )
        }
    }
}
