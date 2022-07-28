package com.example.githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemRowUserBinding

class GithubUserDetailAdapter(private val listUsers: List<ItemsItem>) :
    RecyclerView.Adapter<GithubUserDetailAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val result = listUsers[position]

        Glide.with(holder.itemView.context)
            .load(result.avatarUrl)
            .into(holder.binding.imgItemAvatar)

        holder.binding.tvItemUsername.text = result.login
    }

    override fun getItemCount(): Int = listUsers.size

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)
}