package com.example.githubuser.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubuser.data.local.entity.GithubEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubDao {
    @Query("SELECT * FROM user_favorites")
    fun getAllUserFavorite(): LiveData<List<GithubEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userFavorite: GithubEntity)

    @Delete
    fun delete(userFavorite: GithubEntity)

    @Query("SELECT * FROM user_favorites WHERE id = :id")
    fun getUserFavorite(id: Int): Flow<GithubEntity>

}