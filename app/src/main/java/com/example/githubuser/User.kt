package com.example.githubuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String,
    var avatar: Int,
    var location: String,
    var company: String,
    var username: String,
    var repository: String,
    var followers: String,
    var following: String,
) : Parcelable