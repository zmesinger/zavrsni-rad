package com.mesinger.spaceappxml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

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

    

}

