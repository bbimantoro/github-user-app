package com.example.githubuser.ui.adapter

import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.data.local.entity.GithubEntity
import com.example.githubuser.databinding.ItemUserBinding
import com.example.githubuser.ui.UserDetailActivity

class UserFavoriteAdapter : RecyclerView.Adapter<UserFavoriteAdapter.UserFavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFavoriteViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UserFavoriteViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class UserFavoriteViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userFavorite: GithubEntity) {
            with(binding) {
                tvItemLogin.text = userFavorite.login
                Glide.with(itemView.context)
                    .load(userFavorite.avatarUrl)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgItemAvatar)
                itemView.setOnClickListener {
                    val toDetailUserActivity =
                        Intent(it.context, UserDetailActivity::class.java)
                    it.context.startActivity(toDetailUserActivity)
                }
            }
        }
    }
}