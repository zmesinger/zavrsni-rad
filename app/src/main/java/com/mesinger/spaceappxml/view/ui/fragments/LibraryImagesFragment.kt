package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mesinger.spaceappxml.databinding.FragmentLibraryImagesBinding
import com.mesinger.spaceappxml.viewmodel.APIViewModel

class LibraryImagesFragment : Fragment() {

    private lateinit var binding: FragmentLibraryImagesBinding
    private val viewModel: APIViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryImagesBinding.inflate(inflater, container, false)
        getData()


        return binding.root
    }

    private fun getData(){
        lifecycleScope.launchWhenCreated {
            val response = viewModel.getLibraryImages("Apollo")
            Log.d("LibraryImagesFragment", response.body().toString())


        }


    }

}