package com.example.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var bundle: Bundle
    private var login: String? = null
    private var avatar: String? = null

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_AVATAR = "extra_avatar"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private fun setPagerAdapter(bundle: Bundle) {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, bundle)
        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    private fun setDetailUser(user: GithubUserDetailResponse) {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(user.avatarUrl)
                .into(avatar)

            tvName.text = user.name
            tvUsername.text = user.login
            tvCompany.text = user.company
            tvLocation.text = user.location
            tvRepository.text = user.publicRepos.toString()
            tvFollowers.text = user.followers.toString()
            tvFollowing.text = user.following.toString()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail User"

        login = intent.getStringExtra(EXTRA_USERNAME)
        avatar = intent.getStringExtra(EXTRA_AVATAR)

        viewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MainViewModel::class.java]

        viewModel.detailUser(login)
        viewModel.detailUser.observe(this) {
            setDetailUser(it)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        bundle = Bundle().apply {
            putString(EXTRA_USERNAME, login)
        }
        setPagerAdapter(bundle)
    }
}