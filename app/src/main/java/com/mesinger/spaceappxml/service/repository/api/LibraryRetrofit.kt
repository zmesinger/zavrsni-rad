package com.mesinger.spaceappxml.service.repository.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val LIBRARY_BASE_URL = "https://images-api.nasa.gov/"

object LibraryRetrofit {
    val nasaApi: INasaApi by lazy {
        Retrofit.Builder()
            .baseUrl(LIBRARY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(INasaApi::class.java)
    }
}