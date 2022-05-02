package com.mesinger.spaceappxml.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mesinger.spaceappxml.firebase.FirebaseRepository
import com.mesinger.spaceappxml.service.model.Post

class HomeViewModel(val repo: FirebaseRepository) : ViewModel() {

    private var firestoredb = FirebaseRepository()


    private var postsList: MutableLiveData<List<Post>> = MutableLiveData()

    private fun getPostsObserver(): MutableLiveData<List<Post>>{
        return postsList
    }


    private fun getAllPosts(){



    }

}