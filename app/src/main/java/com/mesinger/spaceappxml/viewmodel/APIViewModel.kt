package com.mesinger.spaceappxml.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.mesinger.spaceappxml.service.model.Base
import com.mesinger.spaceappxml.service.model.Collection
import com.mesinger.spaceappxml.service.model.PictureOfTheDay
import com.mesinger.spaceappxml.service.repository.api.LibraryRetrofit
import com.mesinger.spaceappxml.service.repository.api.RetrofitInstance
import retrofit2.Response

class APIViewModel() : ViewModel() {

    suspend fun getAPOD(): Response<PictureOfTheDay> {
        return RetrofitInstance.nasaApi.getData()
    }

    suspend fun getLibraryImages(): Response<Base> {
        val gson = Gson()
        return LibraryRetrofit.nasaApi.getLibraryImages()
    }
}