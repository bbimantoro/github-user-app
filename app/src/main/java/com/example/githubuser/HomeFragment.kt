package com.example.githubuser

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Github User's"

        binding.rvGithubUsers.setHasFixedSize(true)
        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers: ArrayList<User>
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val listUser = ArrayList<User>()
            for (i in dataName.indices) {
                val user = User(
                    dataName[i],
                    dataAvatar.getResourceId(i, -1),
                    dataLocation[i],
                    dataCompany[i],
                    dataUsername[i],
                    dataRepository[i],
                    dataFollowers[i],
                    dataFollowing[i]
                )
                listUser.add(user)
            }
            dataAvatar.recycle()
            return listUser
        }

    private fun showRecyclerList() {
        if (requireActivity().applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvGithubUsers.layoutManager = GridLayoutManager(requireActivity(), 2)
        } else {
            binding.rvGithubUsers.layoutManager = LinearLayoutManager(requireActivity())
        }
        val listUserAdapter = ListUserAdapter(list)
        binding.rvGithubUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val toDetailFragment =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
                findNavController().navigate(toDetailFragment)
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}