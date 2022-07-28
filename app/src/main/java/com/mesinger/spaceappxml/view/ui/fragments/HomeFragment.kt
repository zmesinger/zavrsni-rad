package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentHomeBinding
import com.mesinger.spaceappxml.view.adapter.postsadapter.OnPostEventListener
import com.mesinger.spaceappxml.view.adapter.postsadapter.PostsListAdapter
import com.mesinger.spaceappxml.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "HomeFragment"
class HomeFragment: Fragment(), OnPostEventListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: PostsListAdapter
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNew()
        setupRecyclerView()
        loadData()
        binding.HomeRecyclerView.invalidate()
    }




    private fun loadData(){
        viewModel.posts.observe(viewLifecycleOwner){
            if( it != null && it.isNotEmpty()){
                adapter.setPosts(it)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.HomeRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = PostsListAdapter()
        adapter.onPostEventListener = this
        binding.HomeRecyclerView.adapter = adapter
    }



    private fun addNew(){
        binding.homeFAB.setOnClickListener(){
            findNavController().navigate(R.id.action_homeFragment_to_addNewPhotoFragment)
        }
    }



    override fun onItemSelectedListener(id: String?) {
        val action = HomeFragmentDirections.actionHomeFragmentToPostDetailFragment(id.toString())
        findNavController().navigate(action)
    }

    override fun onLikeButtonListener(id: String?) {
        Log.d(TAG, "onLikeButtonListener: iv pressed")
    }


}