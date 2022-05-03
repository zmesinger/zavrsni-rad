package com.mesinger.spaceappxml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mesinger.spaceappxml.firebase.FirebaseRepository
import com.mesinger.spaceappxml.service.model.Post

class HomeViewModel(private val repo: FirebaseRepository) : ViewModel() {

    val posts = repo.getAllPosts()

}