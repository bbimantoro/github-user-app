package com.example.githubuser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubuser.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "Detail Fragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Detail User"

        val dataUser = DetailFragmentArgs.fromBundle(arguments as Bundle).user

        Glide.with(requireActivity())
            .load(dataUser.avatar)
            .into(binding.avatar)

        binding.apply {
            tvName.text = dataUser.name
            tvUsername.text = dataUser.username
            tvRepository.text = dataUser.repository
            tvFollowers.text = dataUser.followers
            tvFollowing.text = dataUser.following
        }

        Log.d(TAG, binding.tvName.text.toString())
    }

}