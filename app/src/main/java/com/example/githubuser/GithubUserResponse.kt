package com.example.githubuser

import com.google.gson.annotations.SerializedName

data class GithubUserResponse(

    @field:SerializedName("items")
    val items: List<ItemsItem>
)

data class ItemsItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String

)
