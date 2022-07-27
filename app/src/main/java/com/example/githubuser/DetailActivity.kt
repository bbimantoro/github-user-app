package com.example.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        private const val TAG = "Detail Activity"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail User"

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        val dataUser = intent.getParcelableExtra<User>("DATA")

        Glide.with(this)
            .load(dataUser?.avatar)
            .into(binding.avatar)

        binding.apply {
            tvName.text = dataUser?.name
            tvUsername.text = dataUser?.username
            tvRepository.text = dataUser?.repository
            tvFollowers.text = dataUser?.followers
            tvFollowing.text = dataUser?.following
        }

        Log.d(TAG, dataUser?.name.toString())
    }
}