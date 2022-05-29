package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentPostDetailBinding
import com.mesinger.spaceappxml.service.model.Post
import com.mesinger.spaceappxml.viewmodel.PostDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PostDetailFragment : Fragment(){

    private lateinit var binding: FragmentPostDetailBinding
    private val viewModel: PostDetailViewModel by viewModel()
    private val args: PostDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val post = viewModel.getPostByID(args.postID)
        display(post)


    }

    private fun display(post: Post?) {
        post?.let {
            binding.apply {

                titleTextView.text = post.title
                userTextView.text = post.userEmail
                descriptionTextViw.text = post.description
                Glide.with(requireContext())
                    .load(post.imageURL)
                    .into(cardImageView)

                return
            }
        }
    }




    private fun setContent(){
        binding.commentEditText.doAfterTextChanged {
            viewModel.setContent(it.toString())
        }
    }



}