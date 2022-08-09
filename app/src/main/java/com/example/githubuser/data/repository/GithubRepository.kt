package com.example.githubuser.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.githubuser.data.local.entity.GithubEntity
import com.example.githubuser.data.local.room.GithubDao
import com.example.githubuser.data.local.room.GithubRoomDatabase
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GithubRepository(application: Application) {
    private val mGithubDao: GithubDao
    private val appExecutors: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = GithubRoomDatabase.getInstance(application)
        mGithubDao = db.githubDao()
    }

    fun getAllFavoriteUser(): LiveData<List<GithubEntity>> =
        mGithubDao.getAllUserFavorite().asLiveData()

    fun insert(favoriteUser: GithubEntity) {
        appExecutors.execute { mGithubDao.insert(favoriteUser) }
    }

    fun delete(id: Int) {
        appExecutors.execute { mGithubDao.delete(id) }
    }
}