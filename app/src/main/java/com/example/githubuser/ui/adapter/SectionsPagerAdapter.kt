package com.example.githubuser.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.ui.UserFollowerFragment
import com.example.githubuser.ui.UserFollowingFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment : Fragment ? = null
        val bundle = Bundle()
        when (position) {
            0 -> UserFollowerFragment()
            1 -> UserFollowingFragment()
        }
        fragment?.arguments = bundle
        return fragment as Fragment
    }
}