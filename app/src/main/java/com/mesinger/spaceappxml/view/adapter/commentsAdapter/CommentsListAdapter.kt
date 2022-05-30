package com.mesinger.spaceappxml.view.adapter.commentsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.service.model.Comment

class CommentsListAdapter: RecyclerView.Adapter<CommentsViewHolder>() {
    private val comments = mutableListOf<Comment>()

    fun setComments(comments: List<Comment>){
        this.comments.clear()
        this.comments.addAll(comments)
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)

        return CommentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = comments[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int {
        return comments.count()
    }
}