package com.mesinger.spaceappxml.service.repository.api

import androidx.lifecycle.LiveData
import com.mesinger.spaceappxml.service.model.PictureOfTheDay
import retrofit2.Response
import retrofit2.http.GET

const val APOD = "/planetary/apod?api_key=91jh31GLa2VA0DICTN7CtYDKhimITGTfELpUCyR0"

interface APODInterface {


    @GET(APOD)
    suspend fun getData(): Response<PictureOfTheDay>
}