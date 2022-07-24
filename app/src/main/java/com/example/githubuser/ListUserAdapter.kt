package com.example.githubuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ItemRowUserBinding

class ListUserAdapter(private val listUsers: ArrayList<User>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemCallback
    }

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, avatar, location, company) = listUsers[position]

        Glide.with(holder.itemView.context)
            .load(avatar)
            .into(holder.binding.imgItemAvatar)

        holder.apply {
            binding.tvItemName.text = name
            binding.tvItemLocation.text = location
            binding.tvItemCompany.text = company
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listUsers[holder.adapterPosition])
            }
        }

    }

    override fun getItemCount(): Int = listUsers.size

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}