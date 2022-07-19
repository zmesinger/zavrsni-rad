package com.mesinger.spaceappxml.viewmodel

import androidx.lifecycle.ViewModel
import com.mesinger.spaceappxml.service.model.PictureOfTheDay
import com.mesinger.spaceappxml.service.repository.api.RetrofitInstance
import retrofit2.Response

class APIViewModel() : ViewModel() {

    suspend fun getAPOD(): Response<PictureOfTheDay> {
        return RetrofitInstance.nasaApi.getData()
    }
}