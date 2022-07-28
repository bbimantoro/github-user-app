package com.example.githubuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemRowUserBinding

class ListDetailUserAdapter(private val listUsers: List<ItemsItem>) :
    RecyclerView.Adapter<ListDetailUserAdapter.ListDetailUserViewHolder>() {

    class ListDetailUserViewHolder(var binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListDetailUserViewHolder(
        ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: ListDetailUserViewHolder, position: Int) {
        val result = listUsers[position]

        Glide.with(holder.itemView.context)
            .load(result.avatarUrl)
            .into(holder.binding.imgItemAvatar)

        holder.binding.tvItemUsername.text = result.login
        holder.binding.tvType.text = result.type
    }

    override fun getItemCount(): Int = listUsers.size
}