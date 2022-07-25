package com.mesinger.spaceappxml.service.model.nasalibrary

import com.google.gson.annotations.SerializedName

data class Link(

    @SerializedName("rel")
    val rel : String,
    @SerializedName("prompt")
    val prompt : String,
    @SerializedName("href")
    val href : String

)
