package com.example.githubuser

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getListFollower(
        @Path("username") username: String
    ): Call<ListFollowerResponseItem>

    @GET("users/{username}/following")
    fun getListFollowing(
        @Path("username") username: String
    ): Call<ListFollowerResponse>
}