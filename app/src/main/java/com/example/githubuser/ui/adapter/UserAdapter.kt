package com.example.githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.data.remote.response.ItemsItem
import com.example.githubuser.databinding.ItemUserBinding

class UserAdapter(
    private val listUsers: List<ItemsItem>
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            with(binding) {
                tvItemLogin.text = user.login
                imgItemAvatar.loadImage(user.avatarUrl)
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(listUsers[absoluteAdapterPosition])
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

    interface OnItemClickCallback {
        fun onItemClicked(selectedUser: ItemsItem)
    }
}