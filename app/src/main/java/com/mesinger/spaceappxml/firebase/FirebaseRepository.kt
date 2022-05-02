package com.mesinger.spaceappxml.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebaseRepository {

    private val db = Firebase.firestore

    fun getFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    fun getReference(): StorageReference {
        return FirebaseStorage.getInstance().reference
    }




    fun getAllPosts(){
        db.collection("posts")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Firestore", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firestore", "Error getting documents: ", exception)
            }

    }

    
}