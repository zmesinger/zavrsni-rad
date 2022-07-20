package com.mesinger.spaceappxml.service.model

import com.google.gson.annotations.SerializedName

data class Links(

    @SerializedName("rel")
    val rel : String,
    @SerializedName("prompt")
    val prompt : String,
    @SerializedName("href")
    val href : String

)
