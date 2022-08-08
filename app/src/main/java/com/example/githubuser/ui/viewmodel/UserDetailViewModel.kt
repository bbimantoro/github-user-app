package com.example.githubuser.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.local.entity.GithubEntity
import com.example.githubuser.data.remote.response.GithubUserDetailResponse
import com.example.githubuser.data.remote.retrofit.ApiConfig
import com.example.githubuser.data.repository.GithubRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel(application: Application) : ViewModel() {
    private val mGithubRepository: GithubRepository = GithubRepository(application)

    private val _detailUser = MutableLiveData<GithubUserDetailResponse>()
    val detailUser: LiveData<GithubUserDetailResponse> = _detailUser


    fun detailUser(login: String?) {
        if (login != null) {
            val client = ApiConfig.getApiService().getDetailUser(login)
            client.enqueue(object : Callback<GithubUserDetailResponse> {
                override fun onResponse(
                    call: Call<GithubUserDetailResponse>,
                    response: Response<GithubUserDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        _detailUser.value = response.body()
                    } else {
                        Log.e(TAG, "onFailure : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<GithubUserDetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure : ${t.message.toString()}")
                }
            })
        }
    }

    fun insert(favoriteUser: GithubEntity) {
        mGithubRepository.insert(favoriteUser)
    }

    fun delete(favoriteUser: GithubEntity) {
        mGithubRepository.delete(favoriteUser)
    }

    companion object {
        private const val TAG = "UserDetailViewModel"
    }

}