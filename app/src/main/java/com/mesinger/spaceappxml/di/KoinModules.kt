package com.mesinger.spaceappxml.di

import com.mesinger.spaceappxml.firebase.FirebaseAuth
import com.mesinger.spaceappxml.firebase.FirebaseRepository
import com.mesinger.spaceappxml.service.model.User
import com.mesinger.spaceappxml.viewmodel.AddNewPhotoViewModel
import com.mesinger.spaceappxml.viewmodel.HomeViewModel
import com.mesinger.spaceappxml.viewmodel.RegisterViewModel
import com.mesinger.spaceappxml.viewmodel.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val firebaseModule = module {
        single { FirebaseRepository() }
        single { FirebaseAuth() }
    }

    val dataModule = module {
        single { User(get()) }
    }

    val viewModelModule = module {
        viewModel { AddNewPhotoViewModel(get(), get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { RegisterViewModel(get(), get()) }
        viewModel { SignInViewModel(get()) }
    }




