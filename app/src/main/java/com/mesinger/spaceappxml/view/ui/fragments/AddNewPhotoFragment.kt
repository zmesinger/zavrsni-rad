package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentAddNewPhotoBinding


class AddNewPhotoFragment : Fragment() {

    private lateinit var binding: FragmentAddNewPhotoBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun selectPhotoFromGallery(){
        binding.selectPhotoButton.setOnClickListener(){
            
        }
    }

    }