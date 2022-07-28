package com.example.githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemRowUserBinding

class ListDetailUserAdapter(private val listUsers: List<ItemsItem>) :
    RecyclerView.Adapter<ListDetailUserAdapter.ListDetailUserViewHolder>() {

    class ListDetailUserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowUserBinding.bind(itemView)

        fun bind(user: ItemsItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .into(imgItemAvatar)

                tvItemUsername.text = user.login
                tvType.text = user.type
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailUserViewHolder {
        val binding =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListDetailUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListDetailUserViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size
}