package com.mesinger.spaceappxml.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddNewPhotoViewModel: ViewModel() {

    private var title: String = String()
    private var description: String = String()
    private var imageUri: Uri? = null

    fun setTitle(title: String){
        this.title = title
    }

    fun setDescription(description: String){
        this.description = description
    }

    fun setImageUri(uri: Uri?){
        imageUri = uri
    }

    fun getImageUri(): Uri?{
        return this.imageUri
    }




}