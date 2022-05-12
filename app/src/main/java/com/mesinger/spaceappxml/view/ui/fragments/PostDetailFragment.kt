package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentPostDetailBinding
import com.mesinger.spaceappxml.viewmodel.PostDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PostDetailFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailBinding
    private val viewModel: PostDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }



    private fun setContent(){
        binding.commentEditText.doAfterTextChanged {
            viewModel.setContent(it.toString())
        }
    }



}