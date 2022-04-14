package com.mesinger.spaceappxml.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mesinger.spaceappxml.service.model.UserImage

class HomeViewModel : ViewModel() {

    private var postsList: MutableLiveData<ArrayList<UserImage>> = MutableLiveData()

    private fun getPostsObserver(): MutableLiveData<ArrayList<UserImage>>{
        return postsList
    }


    private fun getPosts(){

    }

}