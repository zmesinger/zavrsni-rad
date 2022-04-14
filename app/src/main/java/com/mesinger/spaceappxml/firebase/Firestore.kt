package com.mesinger.spaceappxml.firebase

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Firestore {

    private val db = Firebase.firestore
    private val auth = Firebase.auth


    fun getCurrentUser(): String {
        val user = auth.currentUser
        var userID: String = String()
        if(user != null){
            userID = user.uid
        }else{
            Log.d("Firestore", "User is not signed in")
        }
        return userID
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