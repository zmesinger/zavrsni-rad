package com.mesinger.spaceappxml.view.adapter.postsadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
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

    fun getCard(): MaterialCardView {
        return ItemCardBinding.bind(itemView).cardView
    }

    fun getImage(): ImageView {
        return ItemCardBinding.bind(itemView).cardImageView
    }

    fun getLikeButton() : ImageView{
        return ItemCardBinding.bind(itemView).imageViewLike

    }

    fun getLikeCount(): TextView{
        return ItemCardBinding.bind(itemView).likeCountTextView
    }


}