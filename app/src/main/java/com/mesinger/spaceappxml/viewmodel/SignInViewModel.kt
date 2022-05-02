package com.mesinger.spaceappxml.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.mesinger.spaceappxml.firebase.FirebaseRepository

class SignInViewModel(private val auth: FirebaseAuth) : ViewModel() {


    private val _user: MutableLiveData<Boolean> = MutableLiveData(false)
    private var email: String = String()
    private var password: String = String()

    val user: LiveData<Boolean>
        get() = _user

    fun getEmail(): String{
        return this.email
    }

    fun setEmail(email: String){
        this.email = email
    }

    fun getPassword(): String{
        return this.password
    }

    fun setPassword(password: String){
        this.password = password
    }

    fun singIn(){

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                _user.value = true
                Log.d("SignInViewModel", "singIn: success")
            }else{
                _user.value = false
                Log.d("SignInViewModel", "signIn: failure")
            }

        }

    }




}