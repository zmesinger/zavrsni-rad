package com.mesinger.spaceappxml.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mesinger.spaceappxml.service.model.User

class RegisterViewModel : ViewModel() {
    private lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore

    private val _name: MutableLiveData<String> = MutableLiveData()
    private val _email: MutableLiveData<String> = MutableLiveData()
    private val _password: MutableLiveData<String> = MutableLiveData()

    val name: LiveData<String> get() = _name
    val email: LiveData<String> get() = _email
    val password: LiveData<String> get() = _password

    fun setName(name: String){
        this._name.value = name
    }

    fun setEmail(email: String){
        this._email.value = email
    }

    fun setPassword(password: String){
        this._password.value = password
    }


    fun register(){
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(_email.value.toString(), _password.value.toString()).addOnCompleteListener { task ->
            if(task.isSuccessful){

                val firebaseUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
                val user = User(firebaseUser.uid, _name.value.toString(), firebaseUser.email!!)
                addUserToFirestore(user)

                Log.d("RegisterViewModel", "registerSuccess")
            }else{
                Log.d("RegisterViewModel", "registerFailure")
            }
        }

    }

    fun signOut(){
        FirebaseAuth.getInstance().signOut()
    }

    private fun addUserToFirestore(userInfo: User){
        db.collection("users")
            .add(userInfo)
            .addOnSuccessListener { documentReference ->
                Log.d("RegisterViewModel", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener{ e ->
                Log.w("RegisterViewModel", "Error adding user", e)
            }
    }

}

