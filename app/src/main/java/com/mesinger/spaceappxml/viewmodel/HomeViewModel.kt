package com.mesinger.spaceappxml.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mesinger.spaceappxml.firebase.Firestore
import com.mesinger.spaceappxml.service.model.Post

class HomeViewModel : ViewModel() {

    private var firestoredb = Firestore()


    private var postsList: MutableLiveData<List<Post>> = MutableLiveData()

    private fun getPostsObserver(): MutableLiveData<List<Post>>{
        return postsList
    }


    private fun getAllPosts(){



    }

}