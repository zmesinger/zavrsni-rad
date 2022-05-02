package com.mesinger.spaceappxml.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuth {

    private val auth = Firebase.auth

    fun getAuth(): FirebaseAuth {
        return auth;
    }

    fun getCurrentUserUID(): String {
        val user = auth.currentUser
        var userID = String()
        if(user != null){
            userID = user.uid
        }else{
            Log.d("Firestore", "User is not signed in")
        }
        return userID
    }

    fun getCurrentUserInfo(): FirebaseUser {
        return auth.currentUser!!
    }

    fun signOut(){
        auth.signOut()
    }

}