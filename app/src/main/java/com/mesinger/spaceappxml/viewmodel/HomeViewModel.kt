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

    private var _user: MutableLiveData<String> = MutableLiveData()

    private val user: LiveData<String> get() = _user

    val posts = repo.getAllPosts()

    private fun setUser(user: String){
        this._user.value = user
    }


    private fun getUserEmail(): String {
        return authentication.getCurrentUserInfo().email.toString()
    }

    fun likePost(postID: String){
        val document = repo.getLikeReference(postID)
        setUser(getUserEmail())
        val like = Like(this.user.value.toString(), postID)

        if(like.user.isNotBlank() || like.user.isNotEmpty()){
            document.add(like)
                .addOnSuccessListener {
                    Log.d(TAG, "likePost: success")
                }
        }

    }

}