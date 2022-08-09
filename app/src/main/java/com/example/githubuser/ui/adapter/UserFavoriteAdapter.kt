package com.example.githubuser.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.data.local.entity.GithubEntity
import com.example.githubuser.databinding.ItemUserBinding
import com.example.githubuser.helper.UserFavoriteDiffCallback
import com.example.githubuser.ui.UserDetailActivity

class UserFavoriteAdapter : RecyclerView.Adapter<UserFavoriteAdapter.UserFavoriteViewHolder>() {
    private val listFavorites = ArrayList<GithubEntity>()

    fun setListFavorites(listFavorites: List<GithubEntity>) {
        val diffCallback = UserFavoriteDiffCallback(this.listFavorites, listFavorites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavorites.clear()
        this.listFavorites.addAll(listFavorites)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFavoriteViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserFavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserFavoriteViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }

    override fun getItemCount(): Int = listFavorites.size

    inner class UserFavoriteViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userFavorite: GithubEntity) {
            with(binding) {
                tvItemLogin.text = userFavorite.login
                imgItemAvatar.loadImage(userFavorite.avatarUrl)
                itemView.setOnClickListener {
                    val i = Intent(it.context, UserDetailActivity::class.java)
                    i.putExtra(UserDetailActivity.EXTRA_LOGIN, userFavorite.login)
                    itemView.context.startActivity(i)
                }
            }
        }
    }

    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
            )
            .into(this)
    }
}