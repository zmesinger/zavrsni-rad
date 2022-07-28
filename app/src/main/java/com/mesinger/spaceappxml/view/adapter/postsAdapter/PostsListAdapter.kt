package com.mesinger.spaceappxml.view.adapter.postsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.service.model.Post

private const val LIKED = "liked"
private const val UNLIKED = "unliked"
class PostsListAdapter: RecyclerView.Adapter<PostsViewHolder>() {
    private val posts = mutableListOf<Post>()
    private var isLiked = false
    private var numberOfLikes = 0



    var onPostEventListener: OnPostEventListener? = null

    fun setPosts(posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
        this.notifyDataSetChanged()
    }

    fun setIsLiked(isLiked: Boolean){
        this.isLiked = isLiked
    }

    fun setNumberOfLikes(numberOfLikes: Int){
        this.numberOfLikes = numberOfLikes
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
            holder.getCard().setOnClickListener { listener.onItemSelectedListener(post.postID) }
            holder.getImage().setOnClickListener{ listener.onItemSelectedListener(post.postID) }
            holder.getLikeButton().setOnClickListener{ listener.onLikeButtonListener(post.postID) }
        }

        checkIfLiked(isLiked, holder.getLikeButton())
        setLikeNumber(numberOfLikes, holder.getLikeCount())

    }



    override fun getItemCount(): Int {
            return posts.count()
    }

    private fun checkIfLiked(isLiked: Boolean, imageViewLike: ImageView){
        if (isLiked){
            imageViewLike.setImageResource(R.drawable.ic_liked)
            imageViewLike.tag = LIKED
        }else{
            imageViewLike.setImageResource(R.drawable.ic_unliked)
            imageViewLike.tag = UNLIKED
        }
    }

    private fun setLikeNumber(likeNumber: Int, likeNumberTextView: TextView){
            likeNumberTextView.setText(likeNumber)
    }



}







