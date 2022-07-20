package com.mesinger.spaceappxml.view.adapter.commentsadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mesinger.spaceappxml.databinding.ItemCommentBinding
import com.mesinger.spaceappxml.service.model.Comment

class CommentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(comment: Comment){

        val binding = ItemCommentBinding.bind(itemView)
            binding.textViewUser.text = comment.user
            binding.textViewContent.text = comment.content


    }
}