package com.mesinger.spaceappxml.viewmodel

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch
import java.util.*

class AddNewPhotoViewModel: ViewModel() {

    private var title: String = String()
    private var description: String = String()
    private var imageUri: Uri? = null
    private var postingSuccessful: Boolean = false
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null


    fun setTitle(title: String) {
        this.title = title
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun setImageUri(uri: Uri?) {
        imageUri = uri
    }

    fun getImageUri(): Uri? {
        return this.imageUri
    }

    fun getPostingSuccessful(): Boolean{
        return this.postingSuccessful
    }

    fun uploadPost() {
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        if (imageUri != null) {

            val ref = storageReference?.child("images/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(imageUri!!)!!.addOnFailureListener(){
                Log.d("AddNewPhotoViewModel", "Upload failure")

            }.addOnSuccessListener {
                Log.d("AddNewPhotoViewModel", "Upload successful")

            }

            postingSuccessful = true

        }else{
            postingSuccessful = false
        }

    }

}







