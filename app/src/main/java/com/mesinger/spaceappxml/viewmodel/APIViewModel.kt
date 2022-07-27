package com.mesinger.spaceappxml.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.mesinger.spaceappxml.service.model.nasalibrary.Base
import com.mesinger.spaceappxml.service.model.PictureOfTheDay
import com.mesinger.spaceappxml.service.model.marsrover.MarsPhoto
import com.mesinger.spaceappxml.service.repository.api.API_KEY
import com.mesinger.spaceappxml.service.repository.api.LibraryRetrofit
import com.mesinger.spaceappxml.service.repository.api.RetrofitInstance
import retrofit2.Response

class APIViewModel() : ViewModel() {

    suspend fun getAPOD(): Response<PictureOfTheDay> {
        return RetrofitInstance.nasaApi.getData()
    }

    suspend fun getLibraryImages(keyword: String): Response<Base> {
        return LibraryRetrofit.nasaApi.getLibraryImages(keyword)
    }

    suspend fun getMarsPhotos(earthDate: String) : Response<MarsPhoto> {
        return RetrofitInstance.nasaApi.getRoverPhotos(earthDate, API_KEY)
    }
}