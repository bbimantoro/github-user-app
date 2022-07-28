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
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size

    class ListViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        private val binding = ItemRowUserBinding.bind(itemView)

        fun bind(items: ItemsItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(items.avatarUrl)
                    .into(imgItemAvatar)

                tvItemUsername.text = items.login
                tvType.text = items.type
            }
        }
    }
}