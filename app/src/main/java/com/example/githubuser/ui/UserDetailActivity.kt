package com.example.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.data.local.entity.GithubEntity
import com.example.githubuser.data.remote.response.GithubUserDetailResponse
import com.example.githubuser.databinding.ActivityUserDetailBinding
import com.example.githubuser.ui.adapter.SectionsPagerAdapter
import com.example.githubuser.ui.viewmodel.UserDetailViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class UserDetailActivity : AppCompatActivity() {
    private lateinit var userDetailViewModel: UserDetailViewModel
    private var login: String? = null
    private var fabState = false
    private var favoriteUser: GithubEntity? = null
    private var detailUser = GithubUserDetailResponse()

    private var _binding: ActivityUserDetailBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        login = intent.getStringExtra(EXTRA_LOGIN)

        userDetailViewModel = obtainViewModel(this@UserDetailActivity)

        userDetailViewModel.detailUser(login)

        userDetailViewModel.detailUser.observe(this) { user ->
            detailUser = user
            setDetailUserData(detailUser)
            favoriteUser = GithubEntity(detailUser.id, detailUser.login)
            userDetailViewModel.getAllFavoriteUser().observe(this) { favoriteList ->
                if (favoriteList != null) {
                    for (data in favoriteList) {
                        if (detailUser.id == data.id) {
                            fabState = true
                            binding?.addFavoriteFab?.setImageResource(R.drawable.ic_favorite)
                        }
                    }
                }
            }
        }

        binding?.addFavoriteFab?.setOnClickListener {
            if (!fabState) {
                binding?.addFavoriteFab?.setImageResource(R.drawable.ic_favorite)
                addUserFavorite(detailUser)
            } else {
                binding?.addFavoriteFab?.setImageResource(R.drawable.ic_favorite_border)
                userDetailViewModel.delete(detailUser.id)
                showSnackbarMessage("Favorite user has been deleted")
            }
        }

        setTabLayoutView()

        supportActionBar?.title = "Detail User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun addUserFavorite(user: GithubUserDetailResponse) {
        favoriteUser.let { favoriteUser ->
            favoriteUser?.id = user.id
            favoriteUser?.login = user.login
            favoriteUser?.avatarUrl = user.avatarUrl
            userDetailViewModel.insert(favoriteUser as GithubEntity)
            showSnackbarMessage("Favorite user has been added")
        }
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
        val factory = FavoriteViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[UserDetailViewModel::class.java]
    }

    private fun setTabLayoutView() {
        val mBundle = Bundle().apply {
            putString(EXTRA_LOGIN, login)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, mBundle)
        binding?.apply {
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(
                tabs, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
            )
            .into(this)
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_SHORT).show()
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
        const val EXTRA_LOGIN = "extra_login"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_follower,
            R.string.tab_following
        )
    }
}