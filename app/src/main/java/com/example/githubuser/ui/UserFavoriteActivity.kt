package com.example.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.databinding.ActivityUserFavoriteBinding
import com.example.githubuser.ui.viewmodel.UserFavoriteViewModel

class UserFavoriteActivity : AppCompatActivity() {

    private lateinit var userFavoriteViewModel: UserFavoriteViewModel

    private var _binding: ActivityUserFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        userFavoriteViewModel = obtainViewModel(this@UserFavoriteActivity)
    }

    private fun obtainViewModel(activity: AppCompatActivity): UserFavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[UserFavoriteViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}