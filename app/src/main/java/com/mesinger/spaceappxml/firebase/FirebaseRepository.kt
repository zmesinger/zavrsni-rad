package com.mesinger.spaceappxml.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mesinger.spaceappxml.service.model.Post

class FirebaseRepository(private val db: FirebaseFirestore) {


    private val posts: MutableLiveData<List<Post>> = MutableLiveData()

    fun getFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    fun getReference(): StorageReference {
        return FirebaseStorage.getInstance().reference
    }

    fun getInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
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

    suspend fun getPostDetails(postID: String): Post? {
        var post: Post? = null

        try {
            val p = getBoardDetailsCallback(postID)
            post = p.toObject(Post::class.java)
            if (post != null) {
                post.postID = p.id
            }
        } catch (e: FirebaseFirestoreException) {
            Log.e("getBoardDetailsNEW", "Error getting board details", e)
        }
        return post
    }

    private suspend  fun getBoardDetailsCallback(postID: String): DocumentSnapshot {
        return getInstance().collection("posts")
            .document(postID)
            .get()

    }


    
}