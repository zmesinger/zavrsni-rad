package com.mesinger.spaceappxml.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mesinger.spaceappxml.databinding.ItemCardBinding
import com.mesinger.spaceappxml.service.model.Post

class PostsListAdapter: RecyclerView.Adapter<PostsListAdapter.PostsViewHolder>() {
    private var posts = mutableListOf<Post>()

    private fun setPostList(posts: List<Post>){
        this.posts = posts.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = posts[position]

    }


    override fun getItemCount(): Int {
        return posts.size
    }

    class PostsViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root){

    }


}