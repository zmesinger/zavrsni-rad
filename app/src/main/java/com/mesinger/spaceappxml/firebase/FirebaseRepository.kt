package com.mesinger.spaceappxml.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mesinger.spaceappxml.service.model.Comment
import com.mesinger.spaceappxml.service.model.Post

private const val TAG = "FirebaseRepository"
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

    fun getDocumentReference(): DocumentReference {
        return db.collection("posts").document()
    }

    fun getCommentsReference(postID: String): CollectionReference {
        return db.collection("posts").document(postID).collection("comments")
    }

    fun getAllPosts(): LiveData<List<Post>>{
        db.collection("posts")
            .addSnapshotListener{snapshot, e ->
                if(e != null){
                    Log.w(TAG, "getAllPosts: ", e)
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    posts.postValue(snapshot.toObjects(Post::class.java))
                }
            }

        return posts
    }



    fun getComments(postID: String) : LiveData<List<Comment>>{
        db.collection("posts")
            .document(postID)
            .collection("comments")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    comments.postValue(snapshot.toObjects(Comment::class.java))
                }
            }
             return comments;

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