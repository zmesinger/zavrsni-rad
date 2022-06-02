package com.mesinger.spaceappxml.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mesinger.spaceappxml.service.model.Comment
import com.mesinger.spaceappxml.service.model.Post
import kotlinx.coroutines.tasks.await

class FirebaseRepository(private val db: FirebaseFirestore) {


    private val posts: MutableLiveData<List<Post>> = MutableLiveData()
    private val post: MutableLiveData<Post> = MutableLiveData()
    private val comments: MutableLiveData<List<Comment>> = MutableLiveData()

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

    fun getCommentsReference(postID: String): CollectionReference {
        return db.collection("posts").document(postID).collection("comments")
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

    fun getAllComments(postID: String): LiveData<List<Comment>>{
        if(comments.value == null){
            db.collection("posts")
                .document(postID)
                .collection("comments")
                .get()
                .addOnSuccessListener { results ->
                    comments.postValue(results.toObjects(Comment::class.java))
                    Log.d("Firestore", "Successfully fetched comments")
                }.addOnFailureListener{ exception ->
                    Log.d("Firestore", "Error getting comments", exception)
                }
        }
        return comments
    }


    fun getPostByID(postID: String): LiveData<Post>{

        db.collection("posts")
            .document(postID)
            .get()
            .addOnSuccessListener { result ->
                post.postValue(result.toObject(Post::class.java))
                Log.d("FirebaseRepository", "PostByID Fetched")
            }
        return post

    }




}