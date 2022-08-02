package com.example.githubuser.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubuser.ApiService
import com.example.githubuser.data.local.entity.GithubEntity
import com.example.githubuser.data.local.room.GithubDao
import com.example.githubuser.data.local.room.GithubRoomDatabase
import com.example.githubuser.utils.AppExecutors
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GithubRepository(application: Application) {
    private val mGithubDao: GithubDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = GithubRoomDatabase.getInstance(application)
        mGithubDao = db.githubDao()
    }

    fun getAllUserFavorite(): LiveData<List<GithubEntity>> = mGithubDao.getAllUserFavorite()

    fun insert(userFavorite: GithubEntity) {
        executorService.execute { mGithubDao.insert(userFavorite) }
    }

    fun delete(userFavorite: GithubEntity) {
        executorService.execute { mGithubDao.delete(userFavorite) }
    }
}