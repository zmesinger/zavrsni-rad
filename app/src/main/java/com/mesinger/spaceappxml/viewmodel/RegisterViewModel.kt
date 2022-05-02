package com.mesinger.spaceappxml.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.mesinger.spaceappxml.firebase.FirebaseAuth
import com.mesinger.spaceappxml.firebase.FirebaseRepository
import com.mesinger.spaceappxml.service.model.User

class RegisterViewModel(private val repo: FirebaseRepository, private val auth: FirebaseAuth) : ViewModel() {



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

        auth.getAuth().createUserWithEmailAndPassword(_email.value.toString(), _password.value.toString()).addOnCompleteListener { task ->
            if(task.isSuccessful){

                val firebaseUser: FirebaseUser = auth.getCurrentUserInfo()
                val user = User(firebaseUser.uid, _name.value.toString(), firebaseUser.email!!)
                addUserToFirestore(user)

                Log.d("RegisterViewModel", "registerSuccess")
            }else{
                Log.d("RegisterViewModel", "registerFailure")
            }
        }

    }

    fun signOut(){
        auth.signOut()
    }

    private fun addUserToFirestore(userInfo: User){
        repo.getFirestore().collection("users")
            .add(userInfo)
            .addOnSuccessListener { documentReference ->
                Log.d("RegisterViewModel", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener{ e ->
                Log.w("RegisterViewModel", "Error adding user", e)
            }
    }

}

