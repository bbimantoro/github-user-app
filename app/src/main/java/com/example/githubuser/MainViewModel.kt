package com.example.githubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _searchUser = MutableLiveData<List<ItemsItem>>()
    val searchUser: LiveData<List<ItemsItem>> = _searchUser

    private val _detailUser = MutableLiveData<GithubUserDetailResponse>()
    val detailUser: LiveData<GithubUserDetailResponse> = _detailUser

    private val _listFollowers = MutableLiveData<List<ItemsItem>>()
    val listFollowers: LiveData<List<ItemsItem>> = _listFollowers

    private val _listFollowing = MutableLiveData<List<ItemsItem>>()
    val listFollowing: LiveData<List<ItemsItem>> = _listFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }

    fun findUser(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchUser(query)
        client.enqueue(object : Callback<GithubUserResponse> {
            override fun onResponse(
                call: Call<GithubUserResponse>,
                response: Response<GithubUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _searchUser.value = response.body()?.items
                } else {
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
    }

    fun detailUser(login: String?) {
        if (login != null) {
            _isLoading.value = true
            val client = ApiConfig.getApiService().getDetailUser(login)
            client.enqueue(object : Callback<GithubUserDetailResponse> {
                override fun onResponse(
                    call: Call<GithubUserDetailResponse>,
                    response: Response<GithubUserDetailResponse>
                ) {
                    _isLoading.value = false
                    Log.e(TAG, response.body().toString())
                    if (response.isSuccessful) {
                        _detailUser.value = response.body()
                    }
                }

                override fun onFailure(call: Call<GithubUserDetailResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure : ${t.message.toString()}")
                }
            })
        }
    }

    fun listFollowersUser(login: String?) {
        if (login != null) {
            _isLoading.value = true
            val client = ApiConfig.getApiService().getListFollowers(login)
            client.enqueue(object : Callback<List<ItemsItem>> {
                override fun onResponse(
                    call: Call<List<ItemsItem>>,
                    response: Response<List<ItemsItem>>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _listFollowers.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure : ${t.message}")
                }
            })
        }
    }

    fun listFollowingUser(login: String?) {
        if (login != null) {
            _isLoading.value = true
            val client = ApiConfig.getApiService().getListFollowing(login)
            client.enqueue(object : Callback<List<ItemsItem>> {
                override fun onResponse(
                    call: Call<List<ItemsItem>>,
                    response: Response<List<ItemsItem>>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _listFollowing.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure : ${t.message.toString()}")
                }
            })
        }
    }

}