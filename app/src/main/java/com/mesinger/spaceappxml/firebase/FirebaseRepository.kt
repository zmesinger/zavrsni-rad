package com.mesinger.spaceappxml.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mesinger.spaceappxml.service.model.Post

class FirebaseRepository(private val db: FirebaseFirestore) {


    private val posts: MutableLiveData<List<Post>> = MutableLiveData()
    private var post: MutableLiveData<List<Post>> = MutableLiveData()

    fun getFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    fun getReference(): StorageReference {
        return FirebaseStorage.getInstance().reference
    }




    fun getAllPosts(): LiveData<List<Post>>{
        if(posts.value == null) {
            db.collection("posts")
                .get()
                .addOnSuccessListener { result ->
                    posts.postValue(result.toObjects(Post::class.java))
                }
                .addOnFailureListener { exception ->
                    Log.d("Firestore", "Error getting documents: ", exception)
                }
        }

        return posts
    }

    fun getPostByID(postID: String):  LiveData<List<Post>>{

        val addOnSuccessListener = db.collection("posts")
            .whereEqualTo("postID", postID)
            .get()
            .addOnSuccessListener { result ->
                post.postValue(result.toObjects(Post::class.java))
            }

        return post


    }


    
}