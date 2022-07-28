package com.mesinger.spaceappxml.di
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mesinger.spaceappxml.firebase.FirebaseAuthentication
import com.mesinger.spaceappxml.firebase.FirebaseRepository
import com.mesinger.spaceappxml.service.model.User
import com.mesinger.spaceappxml.service.repository.api.RetrofitInstance
import com.mesinger.spaceappxml.viewmodel.*
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    fun provideDatabase() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
    single { FirebaseRepository(provideDatabase()) }
}

val authModule = module {
    fun provideAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    single { FirebaseAuthentication(provideAuth()) }
}



    val dataModule = module {
        single { User(get()) }
    }





    val viewModelModule = module {
        viewModel { AddNewPhotoViewModel(get(), get()) }
        viewModel { HomeViewModel(get(), get()) }
        viewModel { RegisterViewModel(get(), get()) }
        viewModel { SignInViewModel(get()) }
        viewModel { PostDetailViewModel(get(), get()) }
        viewModel { PictureOfTheDayViewModel(get())}
        viewModel { APIViewModel() }
    }




