package com.example.githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.local.entity.GithubEntity
import com.example.githubuser.data.repository.GithubRepository

class UserFavoriteViewModel(application: Application) : ViewModel() {
    private val mGithubRepository: GithubRepository = GithubRepository(application)

    fun getAllFavoriteUser(): LiveData<List<GithubEntity>> =
        mGithubRepository.getAllFavoriteUser()
}