package com.mesinger.spaceappxml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mesinger.spaceappxml.firebase.FirebaseAuthentication
import com.mesinger.spaceappxml.firebase.FirebaseRepository
import com.mesinger.spaceappxml.service.model.Post

class PostDetailViewModel(private val repo: FirebaseRepository, private val authentication: FirebaseAuthentication) : ViewModel() {

    private var _user: MutableLiveData<String> = MutableLiveData()
    private var _content: MutableLiveData<String> = MutableLiveData()
    private var _postID: MutableLiveData<String> = MutableLiveData()


    private val user: LiveData<String> get() = _user
    private val content: LiveData<String> get() = _content
    private val postID: LiveData<String> get() = _postID

    fun setUser(user: String){
        this._user.value = user
    }

    fun setContent(content: String){
        this._content.value = content
    }

    fun setPostID(postID: String){
        this._postID.value = postID
    }

    fun getUserEmail(): String {
        return authentication.getCurrentUserInfo().email.toString()
    }




}