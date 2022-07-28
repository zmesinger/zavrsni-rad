package com.mesinger.spaceappxml.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mesinger.spaceappxml.firebase.FirebaseAuthentication
import com.mesinger.spaceappxml.firebase.FirebaseRepository
import com.mesinger.spaceappxml.service.model.Like
import com.mesinger.spaceappxml.service.model.Post

private const val TAG = "HomeViewModel"
class HomeViewModel(private val repo: FirebaseRepository, private val authentication: FirebaseAuthentication) : ViewModel() {

    val posts = repo.getAllPosts()


    private fun getUserEmail(): String {
        return authentication.getCurrentUserInfo().email.toString()
    }

    fun likePost(postID: String) {
        val document = repo.getLikeReference(postID)


    }

    fun checkIfLike(postID: String): Boolean {
        return repo.checkIfLiked(postID, authentication.getCurrentUserInfo())
    }

    fun getLikeCount(postID: String): Int {
        return repo.getLikeCount(postID)
    }
}