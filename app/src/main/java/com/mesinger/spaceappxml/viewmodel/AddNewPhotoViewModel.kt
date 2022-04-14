package com.mesinger.spaceappxml.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mesinger.spaceappxml.firebase.Firestore
import com.mesinger.spaceappxml.service.model.UserImage
import java.util.*

class AddNewPhotoViewModel: ViewModel() {
    private val db = Firebase.firestore
    private val firebaseAuth = Firestore()

    private var title: String = String()
    private var description: String = String()
    private var imageUri: Uri? = null
    private var username: String = String()

    private var postingSuccessful: Boolean = false

    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null



    fun setUsername(username: String){
       this.username = username
    }

    fun getUsername(): String{
        return this.username
    }
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

    fun uploadImageToCloudStorage() {
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference


        if (imageUri != null) {

            val ref = storageReference?.child("images/" + UUID.randomUUID().toString())

            val uploadTask = ref?.putFile(imageUri!!)!!.addOnFailureListener(){
                Log.d("AddNewPhotoViewModel", "Upload failure")

            }.addOnSuccessListener { it ->
                val result = it.metadata!!.reference!!.downloadUrl
                result.addOnSuccessListener {
                    var imageLink = it.toString()
                    val post = UserImage(this.title,this.description, imageLink, firebaseAuth.getCurrentUser())
                    uploadPost(post)
                    Log.d("AddNewPhotoViewModel", "Upload post successful")
                }
                Log.d("AddNewPhotoViewModel", "Upload successful")

            }

            postingSuccessful = true

        }else{
            postingSuccessful = false
        }

    }

    private fun uploadPost(post: UserImage){

        db.collection("posts")
            .add(post)

    }

}







