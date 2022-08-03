package com.example.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.data.remote.response.GithubUserDetailResponse
import com.example.githubuser.databinding.ActivityUserDetailBinding
import com.example.githubuser.ui.adapter.SectionsPagerAdapter
import com.example.githubuser.ui.viewmodel.UserDetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserDetailActivity : AppCompatActivity() {

    private lateinit var userDetailViewModel: UserDetailViewModel

    private var _binding: ActivityUserDetailBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        userDetailViewModel = obtainViewModel(this@UserDetailActivity)

        userDetailViewModel.detailUser.observe(this) { user ->
            setDetailUserData(user)
        }


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding?.apply {
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(
                tabs, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        supportActionBar?.title = "Detail User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun setDetailUserData(user: GithubUserDetailResponse) {
        binding?.apply {
            avatar.loadImage(user.avatarUrl)
            tvUsername.text = user.login
            tvName.text = user.name
            tvRepository.text = user.publicRepos.toString()
            tvFollowers.text = user.followers.toString()
            tvFollowing.text = user.following.toString()
            tvLocation.text = user.location
            tvCompany.text = user.company
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): UserDetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[UserDetailViewModel::class.java]
    }

    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
            )
            .into(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_follower,
            R.string.tab_following
        )
    }
}