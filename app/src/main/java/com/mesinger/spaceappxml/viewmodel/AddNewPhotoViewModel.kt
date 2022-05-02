package com.mesinger.spaceappxml.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mesinger.spaceappxml.firebase.FirebaseAuth
import com.mesinger.spaceappxml.firebase.FirebaseRepository
import com.mesinger.spaceappxml.service.model.Post
import java.util.*

class AddNewPhotoViewModel(private val repo: FirebaseRepository, private val auth: FirebaseAuth): ViewModel() {


    private var title: String = String()
    private var description: String = String()
    private var imageUri: Uri? = null
    private var username: String = String()

    private var postingSuccessful: Boolean = false




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

       val storageReference = repo.getReference()


        if (imageUri != null) {

            val ref = storageReference.child("images/" + UUID.randomUUID().toString())

            val uploadTask = ref.putFile(imageUri!!)!!.addOnFailureListener(){
                Log.d("AddNewPhotoViewModel", "Upload failure")

            }.addOnSuccessListener { it ->
                val result = it.metadata!!.reference!!.downloadUrl
                result.addOnSuccessListener {
                    var imageLink = it.toString()
                    val post = Post(this.title,this.description, imageLink, auth.getCurrentUserUID())
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

    private fun uploadPost(post: Post){

        repo.getFirestore()
            .collection("posts")
            .add(post)

    }

}







