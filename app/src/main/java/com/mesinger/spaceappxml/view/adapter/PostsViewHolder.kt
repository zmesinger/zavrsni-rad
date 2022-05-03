package com.mesinger.spaceappxml.view.adapter

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mesinger.spaceappxml.databinding.ItemCardBinding
import com.mesinger.spaceappxml.service.model.Post

class PostsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {



    fun bind(post: Post){
        val binding = ItemCardBinding.bind(itemView)
        binding.titleTextView.text = post.title
        binding.descriptionTextViw.text = post.description
        binding.userTextView.text = post.userID
        binding.cardImageView.setImageURI(Uri.parse(post.imageURL))
    }

}