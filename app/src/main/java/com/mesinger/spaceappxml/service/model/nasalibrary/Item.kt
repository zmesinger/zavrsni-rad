package com.mesinger.spaceappxml.service.model.nasalibrary

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("href")
    val href : String,
    @SerializedName("data")
    val data : List<Data>,
    @SerializedName("links")
    val links : List<Link>
)
