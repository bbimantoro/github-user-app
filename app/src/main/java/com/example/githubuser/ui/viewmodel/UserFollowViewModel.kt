package com.example.githubuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.remote.response.ItemsItem
import com.example.githubuser.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFollowViewModel : ViewModel() {
    private val _listFollower = MutableLiveData<List<ItemsItem>>()
    val listFollower: LiveData<List<ItemsItem>> = _listFollower

    private val _listFollowing = MutableLiveData<List<ItemsItem>>()
    val listFollowing: LiveData<List<ItemsItem>> = _listFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getFollowerUsers(login: String?) {
        if (login != null) {
            _isLoading.value = true
            val client = ApiConfig.getApiService().getFollowUser(login, "followers")
            client.enqueue(object : Callback<List<ItemsItem>> {
                override fun onResponse(
                    call: Call<List<ItemsItem>>,
                    response: Response<List<ItemsItem>>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _listFollower.value = response.body()
                    } else {
                        Log.e(TAG, "onFailure : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure : ${t.message.toString()}")
                }
            })
        }
    }

    fun getFollowingUsers(login: String?) {
        if (login != null) {
            _isLoading.value = true
            val client = ApiConfig.getApiService().getFollowUser(login, "following")
            client.enqueue(object : Callback<List<ItemsItem>> {
                override fun onResponse(
                    call: Call<List<ItemsItem>>,
                    response: Response<List<ItemsItem>>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _listFollowing.value = response.body()
                    } else {
                        Log.e(TAG, "onFailure : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure : ${t.message.toString()}")
                }
            })
        }
    }

    companion object {
        const val TAG = "UserFollowViewModel"
    }
}