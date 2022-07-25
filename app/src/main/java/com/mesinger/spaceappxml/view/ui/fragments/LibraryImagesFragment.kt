package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.api.LogDescriptor
import com.mesinger.spaceappxml.databinding.FragmentLibraryImagesBinding
import com.mesinger.spaceappxml.view.adapter.libraryadapter.LibraryListAdapter
import com.mesinger.spaceappxml.view.adapter.postsadapter.PostsListAdapter
import com.mesinger.spaceappxml.viewmodel.APIViewModel

private const val TAG = "LibraryImagesFragment"

class LibraryImagesFragment : Fragment() {

    private lateinit var binding: FragmentLibraryImagesBinding
    private lateinit var adapter: LibraryListAdapter
    private val viewModel: APIViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryImagesBinding.inflate(inflater, container, false)
        setupRecyclerView()
        loadData()
        //getData()


        return binding.root
    }

    private fun loadData() {
        lifecycleScope.launchWhenCreated {
            val response = viewModel.getLibraryImages()
            if(response.isSuccessful){
                adapter.setItems(response.body()!!.collection.items)
            }else{
                Log.d(TAG, "loadData: failure")
            }
        }
    }

    private fun setupRecyclerView() {
        binding.libraryRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = LibraryListAdapter()
        binding.libraryRecyclerView.adapter = adapter
    }

    private fun getData(){
        lifecycleScope.launchWhenCreated {
            val response = viewModel.getLibraryImages()
            if(response.isSuccessful){
                Log.d(TAG, "getData: " + response.body()!!.collection.items[0].href)
            }else{
                Log.d(TAG, "getData: failure")
            }


        }


    }

}