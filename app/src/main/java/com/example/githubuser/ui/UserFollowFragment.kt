package com.example.githubuser.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.data.remote.response.ItemsItem
import com.example.githubuser.databinding.FragmentUserBinding
import com.example.githubuser.ui.adapter.UserFollowAdapter
import com.example.githubuser.ui.viewmodel.UserFollowViewModel
import com.google.android.material.divider.MaterialDividerItemDecoration

class UserFollowFragment : Fragment() {
    private val userFollowViewModel by viewModels<UserFollowViewModel>()
    private var tabName: String? = null
    private var login: String? = null

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabName = arguments?.getString(ARG_TAB)
        login = arguments?.getString(UserDetailActivity.EXTRA_LOGIN)

        if (tabName == TAB_FOLLOWER) {
            userFollowViewModel.listFollower.observe(this.viewLifecycleOwner) { items ->
                setFollowerUserData(items)
            }
            userFollowViewModel.getFollowerUsers(login)
        } else if (tabName == TAB_FOLLOWING) {
            userFollowViewModel.listFollowing.observe(this.viewLifecycleOwner) { items ->
                setFollowingUserData(items)
            }
            userFollowViewModel.getFollowingUsers(login)
        }

        userFollowViewModel.isLoading.observe(this.viewLifecycleOwner) {
            showLoading(it)
        }

        val layoutManager = LinearLayoutManager(this.context)
        binding?.rvUsers?.layoutManager = layoutManager
        val mItemDecoration =
            MaterialDividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding?.rvUsers?.apply {
            setHasFixedSize(true)
            addItemDecoration(mItemDecoration)
        }

    }

    private fun setFollowerUserData(items: List<ItemsItem>) {
        val adapter = UserFollowAdapter(items)
        binding?.rvUsers?.adapter = adapter
    }

    private fun setFollowingUserData(items: List<ItemsItem>) {
        val adapter = UserFollowAdapter(items)
        binding?.rvUsers?.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARG_TAB = "tab_name"
        const val TAB_FOLLOWER = "tab_follower"
        const val TAB_FOLLOWING = "tab_following"
    }
}