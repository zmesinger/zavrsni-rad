package com.mesinger.spaceappxml.service.repository.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.nasa.gov/"
object RetrofitInstance {

    val nasaApi: INasaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(INasaApi::class.java)
    }

}