package com.mesinger.spaceappxml.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mesinger.spaceappxml.service.model.Post
import kotlinx.coroutines.tasks.await

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

    fun getDocumentReference(): DocumentReference {
        return db.collection("posts").document()
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
    fun getPostByID(postID: String): Post{

        var post: Post? = Post()
        db.collection("posts")
            .document(postID)
            .get()
            .addOnSuccessListener { result ->
                post = result.toObject(Post::class.java)
                Log.d("FirebaseRepository", "PostByID Fetched")
                Log.d("FirebaseRepository", post!!.postID)
                Log.d("FirebaseRepository", post!!.title)
                Log.d("FirebaseRepository", post!!.description)
                Log.d("FirebaseRepository", post!!.userEmail)
            }
        return post!!

    }




}