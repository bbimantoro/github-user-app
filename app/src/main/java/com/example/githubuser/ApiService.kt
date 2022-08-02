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

    @GET("users/{username}/{type}")
    fun getFollowerFollowingUser(
        @Path("username") username: String,
        @Path("type") type: String
    ): Call<List<ItemsItem>>
}