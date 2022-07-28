package com.example.githubuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemRowUserBinding

class ListUserAdapter(private val listUsers: List<ItemsItem>) :
    RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemCallback
    }

    class ListUserViewHolder(var binding: ItemRowUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListUserViewHolder(
        ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val result = listUsers[position]

        Glide.with(holder.itemView.context)
            .load(result.avatarUrl)
            .into(holder.binding.imgItemAvatar)

        holder.binding.tvItemUsername.text = result.login
        holder.binding.tvType.text = result.type

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUsers[holder.adapterPosition])
        }

    }

    override fun getItemCount(): Int = listUsers.size

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsItem)
    }
}