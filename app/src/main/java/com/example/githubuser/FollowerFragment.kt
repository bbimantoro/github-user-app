package com.example.githubuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.ItemRowUserDetailBinding

class FollowerFragment : Fragment() {

    private var _binding: ItemRowUserDetailBinding? = null
    private val binding get() = _binding!!
    private val followerViewModel by viewModels<FollowerViewModel>()

    private var login: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ItemRowUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login = arguments?.getString(DetailActivity.EXTRA_USERNAME)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvGithubUsers.layoutManager = layoutManager

        followerViewModel.listFollowers.observe(viewLifecycleOwner) {
            setFollowersUser(it)
        }

        followerViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        followerViewModel.getFollowerUser(login)
    }

    private fun setFollowersUser(items: List<ItemsItem>) {
        val adapter = GithubUserDetailAdapter(items)
        binding.rvGithubUsers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}