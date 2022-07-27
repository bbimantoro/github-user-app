package com.example.githubuser

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @field:SerializedName("items")
    val items: List<ItemsItem>
)

data class ItemsItem(

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("id")
    val id: Int

)
