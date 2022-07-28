package com.example.githubuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.ItemRowUserDetailBinding

class FollowingFragment : Fragment() {

    private var _binding: ItemRowUserDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FollowingViewModel
    private var login: String? = null

    private fun setFollowingUser(items: List<ItemsItem>) {
        val adapter = GithubUserDetailAdapter(items)
        binding.rvGithubUsers.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

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

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FollowingViewModel::class.java]

        binding.rvGithubUsers.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvGithubUsers.layoutManager = layoutManager

        viewModel.listFollowing.observe(viewLifecycleOwner) {
            setFollowingUser(it)
        }
        viewModel.getFollowingUser(login)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}