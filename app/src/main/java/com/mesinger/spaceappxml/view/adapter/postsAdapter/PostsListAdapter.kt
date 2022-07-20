package com.mesinger.spaceappxml.view.adapter.postsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.service.model.Post

class PostsListAdapter: RecyclerView.Adapter<PostsViewHolder>() {
    private val posts = mutableListOf<Post>()
    var onPostEventListener: OnPostEventListener? = null

    fun setPosts(posts: List<Post>){
        this.posts.clear()
        this.posts.addAll(posts)
        this.notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        return PostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)

        onPostEventListener?.let { listener ->
                holder.itemView.setOnClickListener { listener.onItemSelectedListener(post.postID)
                  }
                }

            }

    override fun getItemCount(): Int {
        return posts.count()
    }
}






