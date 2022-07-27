package com.example.githubuser

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<GithubUserResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<GithubUserDetailResponse>

    @GET("users/{username}/followers")
    fun getListFollowers(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getListFollowing(
        @Path("username") username: String
    ): Call<List<ItemsItem>>
}