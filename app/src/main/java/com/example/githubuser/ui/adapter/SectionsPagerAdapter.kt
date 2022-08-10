package com.example.githubuser.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.ui.UserFollowFragment

class SectionsPagerAdapter(activity: AppCompatActivity, private val mBundle: Bundle) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        val fragment = UserFollowFragment()
        if (position == 0) {
            mBundle.putString(UserFollowFragment.ARG_TAB, UserFollowFragment.TAB_FOLLOWER)
        } else {
            mBundle.putString(UserFollowFragment.ARG_TAB, UserFollowFragment.TAB_FOLLOWING)
        }
        fragment.arguments = mBundle
        return fragment
    }

    companion object {
        private const val ITEM_COUNT = 2
    }
}