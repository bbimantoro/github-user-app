package com.example.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.databinding.ActivityUserFavoriteBinding
import com.example.githubuser.ui.adapter.UserFavoriteAdapter
import com.example.githubuser.ui.viewmodel.UserFavoriteViewModel
import com.google.android.material.divider.MaterialDividerItemDecoration

class UserFavoriteActivity : AppCompatActivity() {
    private lateinit var userFavoriteViewModel: UserFavoriteViewModel
    private lateinit var adapter: UserFavoriteAdapter

    private var _binding: ActivityUserFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        userFavoriteViewModel = obtainViewModel(this@UserFavoriteActivity)
        userFavoriteViewModel.getAllFavoriteUser().observe(this) { user ->
            if (user != null) {
                adapter.setListFavorites(user)
            }
        }

        adapter = UserFavoriteAdapter()
        val layoutManager = LinearLayoutManager(this)
        binding?.rvUserFavorite?.layoutManager = layoutManager
        binding?.rvUserFavorite?.adapter = adapter
        val mItemDecoration = MaterialDividerItemDecoration(this, layoutManager.orientation)
        binding?.rvUserFavorite?.apply {
            setHasFixedSize(true)
            addItemDecoration(mItemDecoration)
        }

        supportActionBar?.title = getString(R.string.appbar_title_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun obtainViewModel(activity: AppCompatActivity): UserFavoriteViewModel {
        val factory = FavoriteViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[UserFavoriteViewModel::class.java]
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}