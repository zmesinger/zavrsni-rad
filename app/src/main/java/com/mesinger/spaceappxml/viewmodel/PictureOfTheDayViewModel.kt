package com.mesinger.spaceappxml.viewmodel

import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.mesinger.spaceappxml.service.repository.api.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class PictureOfTheDayViewModel(private val retrofitInstance: RetrofitInstance): ViewModel() {



    fun getData(){

    }

}