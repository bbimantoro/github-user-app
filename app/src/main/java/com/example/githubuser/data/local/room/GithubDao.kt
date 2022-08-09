package com.example.githubuser.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubuser.data.local.entity.GithubEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubDao {
    @Query("SELECT * FROM user_favorites")
    fun getAllUserFavorite(): Flow<List<GithubEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userFavorite: GithubEntity)

    @Query("DELETE FROM user_favorites WHERE id = :id")
    fun delete(id: Int): Int

    @Query("SELECT * FROM user_favorites WHERE id = :id")
    fun getUserById(id: Int): LiveData<GithubEntity>

}