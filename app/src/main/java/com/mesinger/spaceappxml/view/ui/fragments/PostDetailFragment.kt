package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mesinger.spaceappxml.databinding.FragmentPostDetailBinding
import com.mesinger.spaceappxml.service.model.Post
import com.mesinger.spaceappxml.view.adapter.commentsadapter.CommentsListAdapter
import com.mesinger.spaceappxml.viewmodel.PostDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "PostDetailFragment"
class PostDetailFragment : Fragment(){

    private lateinit var binding: FragmentPostDetailBinding
    private lateinit var adapter: CommentsListAdapter
    private val viewModel: PostDetailViewModel by viewModel()
    private val args: PostDetailFragmentArgs by navArgs()
    private lateinit var post: LiveData<Post>
    private var postID = String()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        post = viewModel.getPostByID(args.postID)
        setupRecyclerView()
        display(post)
        loadComments()
        setContent()
        postComment()


    }

    private fun loadComments() {
        viewModel.getComments(args.postID).observe(viewLifecycleOwner){
            if( it != null && it.isNotEmpty()){
                if(postID == args.postID){
                    adapter.setComments(it)
                }
            }

        }
        Log.d(TAG, "Comments displayed")


    }

    private fun setupRecyclerView() {
        binding.commentsRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = CommentsListAdapter()
        binding.commentsRecyclerView.adapter = adapter
        Log.d(TAG, "RecyclerView set up")
    }

    private fun display(post: LiveData<Post>) {
        post.observe(viewLifecycleOwner){
            binding.apply {
                postID = it.postID
                titleTextView.text = it.title
                userTextView.text = it.userEmail
                descriptionTextViw.text = it.description
                Glide.with(requireContext())
                    .load(it.imageURL)
                    .into(cardImageView)
            }
        }
        Log.d(TAG, "Post displayed")
    }

    private fun postComment(){
        binding.commentButton.setOnClickListener{
            viewModel.uploadComment(args.postID)
            Log.d(TAG, "Commented successfully")
            binding.commentEditText.text!!.clear()
        }
    }




    private fun setContent(){
        binding.commentEditText.doAfterTextChanged {
            viewModel.setContent(it.toString())
            }
        }
    }




