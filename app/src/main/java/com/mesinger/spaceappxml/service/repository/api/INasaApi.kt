package com.mesinger.spaceappxml.service.repository.api

import com.mesinger.spaceappxml.service.model.nasalibrary.Base
import com.mesinger.spaceappxml.service.model.PictureOfTheDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val APOD = "/planetary/apod?api_key=91jh31GLa2VA0DICTN7CtYDKhimITGTfELpUCyR0"
const val NASA_LIBRARY = "search"


interface INasaApi {


    @GET(APOD)
    suspend fun getData(): Response<PictureOfTheDay>

    @GET(NASA_LIBRARY)
    suspend fun getLibraryImages(@Query("keywords") keyword: String) : Response<Base>

}