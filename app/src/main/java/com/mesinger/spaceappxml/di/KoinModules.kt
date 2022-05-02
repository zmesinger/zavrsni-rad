package com.mesinger.spaceappxml.di

import com.google.firebase.firestore.FirebaseFirestore
import com.mesinger.spaceappxml.firebase.FirebaseRepository
import com.mesinger.spaceappxml.service.model.User
import com.mesinger.spaceappxml.viewmodel.AddNewPhotoViewModel
import com.mesinger.spaceappxml.viewmodel.HomeViewModel
import com.mesinger.spaceappxml.viewmodel.RegisterViewModel
import com.mesinger.spaceappxml.viewmodel.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

    val firebaseModule = module {
        fun provideFirestore(): FirebaseFirestore {
            return FirebaseRepository().getFirestore()
        }
    }

    val dataModule = module {
        single { User(get()) }
    }

    val viewModelModule = module {
        viewModel { AddNewPhotoViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { SignInViewModel(get()) }
    }




