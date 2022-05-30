package com.mesinger.spaceappxml.view.adapter.postsAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.ItemCardBinding
import com.mesinger.spaceappxml.service.model.Post

class PostsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(post: Post){

        val binding = ItemCardBinding.bind(itemView)
        binding.titleTextView.text = post.title
        binding.descriptionTextViw.text = post.description
        binding.userTextView.text = post.userEmail
        Glide.with(itemView)
            .load(post.imageURL)
            .into(binding.cardImageView)
    }


}