package com.mesinger.spaceappxml.service.model

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("href")
    val href : String,
    @SerializedName("data")
    val data : List<Data>,
    @SerializedName("links")
    val links : List<Links>
)
