package com.example.githubuser.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.githubuser.data.local.entity.GithubEntity

class UserFavoriteDiffCallback(
    private val mOldUserFavoriteList: List<GithubEntity>,
    private val mNewUserFavoriteList: List<GithubEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldUserFavoriteList.size
    }

    override fun getNewListSize(): Int {
        return mNewUserFavoriteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserFavoriteList[oldItemPosition].id == mNewUserFavoriteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldUserFavoriteList[oldItemPosition]
        val newEmployee = mNewUserFavoriteList[newItemPosition]
        return oldEmployee.login == newEmployee.login && oldEmployee.avatarUrl == newEmployee.avatarUrl
    }
}