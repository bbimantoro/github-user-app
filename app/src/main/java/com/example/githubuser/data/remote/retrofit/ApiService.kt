package com.example.githubuser.data.remote.retrofit

import com.example.githubuser.data.remote.response.GithubUserDetailResponse
import com.example.githubuser.data.remote.response.GithubUserResponse
import com.example.githubuser.data.remote.response.ItemsItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getUser(
        @Query("q") q: String,
    ): Call<GithubUserResponse>

    @GET("users/{login}")
    fun getDetailUser(
        @Path("login") login: String
    ): Call<GithubUserDetailResponse>

    @GET("users/{login}/{type}")
    fun getFollowUser(
        @Path("login") login: String,
        @Path("type") type: String
    ): Call<List<ItemsItem>>
}