package com.mesinger.spaceappxml.service.repository.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val BASE_URL = "https://api.nasa.gov/"
object RetrofitInstance {

    val apodApi: APODInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APODInterface::class.java)
    }

}