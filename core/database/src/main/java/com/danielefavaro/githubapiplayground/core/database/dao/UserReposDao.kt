package com.danielefavaro.githubapiplayground.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danielefavaro.githubapiplayground.core.database.model.UserRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserReposDao {
    @Query("SELECT * FROM user_repository")
    fun getUserRepos(): Flow<List<UserRepoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRepos(items: List<UserRepoEntity>)

    @Query("SELECT * FROM user_repository WHERE id=:id")
    suspend fun getUseRepo(id: Long): UserRepoEntity
}
