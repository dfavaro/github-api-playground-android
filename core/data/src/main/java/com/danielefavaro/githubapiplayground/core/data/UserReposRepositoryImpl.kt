package com.danielefavaro.githubapiplayground.core.data

import com.danielefavaro.githubapiplayground.core.data.domain.UserReposRepository
import com.danielefavaro.githubapiplayground.core.data.model.asBusiness
import com.danielefavaro.githubapiplayground.core.data.model.asEntity
import com.danielefavaro.githubapiplayground.core.database.dao.UserReposDao
import com.danielefavaro.githubapiplayground.core.model.UserRepoModel
import com.danielefavaro.githubapiplayground.core.network.domain.UserReposDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserReposRepositoryImpl @Inject constructor(
    private val userReposDao: UserReposDao,
    private val userReposDataSource: UserReposDataSource
) : UserReposRepository {

    /**
     * Listen for changes on db
     *
     * @return the user's repositories business model [UserRepoModel]
     */
    override suspend fun getUserRepos(): Flow<List<UserRepoModel>> =
        userReposDao.getUserRepos().map {
            it.map { entity ->
                entity.asBusiness()
            }
        }

    /**
     * Cache user's repositories from BE [UserRepoModel]
     */
    override suspend fun updateUserRepos() {
        userReposDataSource.getUserRepos()
            .map {
                it.asEntity()
            }
            .sortedBy {
                it.owner.login
            }
            .let {
                userReposDao.insertUserRepos(it)
            }
    }

    /**
     * Get user repo from database
     *
     * @return the repository [UserRepoModel]
     */
    override suspend fun getUserRepo(id: Long): UserRepoModel =
        userReposDao.getUseRepo(id = id).asBusiness()
}
