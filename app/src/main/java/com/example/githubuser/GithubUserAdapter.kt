package com.example.githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.githubuser.databinding.ItemRowUserBinding

class GithubUserAdapter(private val listUsers: List<ItemsItem>) :
    RecyclerView.Adapter<GithubUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val result = listUsers[position]

        Glide.with(holder.itemView.context)
            .load(result.avatarUrl)
            .thumbnail(
                Glide.with(holder.itemView.context)
                    .load(R.drawable.ic_baseline_image_not_supported_24)
            )
            .into(holder.binding.imgItemAvatar)

        holder.binding.tvItemUsername.text = result.login

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUsers[holder.adapterPosition])
        }

    }

    override fun getItemCount(): Int = listUsers.size

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemsItem)
    }
}