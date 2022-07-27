package com.mesinger.spaceappxml.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mesinger.spaceappxml.firebase.FirebaseAuthentication
import com.mesinger.spaceappxml.firebase.FirebaseRepository
import com.mesinger.spaceappxml.service.model.Comment
import com.mesinger.spaceappxml.service.model.Post

class PostDetailViewModel(private val repo: FirebaseRepository, private val authentication: FirebaseAuthentication) : ViewModel() {

    private var _user: MutableLiveData<String> = MutableLiveData()
    private var _content: MutableLiveData<String> = MutableLiveData()
    private var _post: MutableLiveData<Post> = MutableLiveData()


    private val post: LiveData<Post> get() = _post
    private val user: LiveData<String> get() = _user
    private val content: LiveData<String> get() = _content



    private fun setUser(user: String){
        this._user.value = user
    }

    private fun setPost(post: Post){
        this._post.value = post
    }

    fun setContent(content: String){
        this._content.value = content
    }


    private fun getUserEmail(): String {
        return authentication.getCurrentUserInfo().email.toString()
    }

    fun getPostByID(postID: String): LiveData<Post> {
        return repo.getPostByID(postID)
    }

    fun getComments(postID: String): LiveData<List<Comment>>{
        return repo.getComments(postID)
    }

    fun uploadComment(postID: String){
        val document = repo.getCommentsReference(postID)
        setUser(getUserEmail())
        val comment = Comment(this.user.value.toString(),this.content.value.toString(),postID)

        if(comment.content.isNotBlank()) {
            document
                .add(comment)
                .addOnSuccessListener {
                    Log.d("PostDetailViewModel", "Comment successful")
                }
        }

    }






}