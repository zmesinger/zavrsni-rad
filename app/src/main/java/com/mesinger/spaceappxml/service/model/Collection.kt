package com.mesinger.spaceappxml.service.model

import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("href")
    val href : String,
    @SerializedName("items")
    val items : List<Items>,
    @SerializedName("links")
    val links : List<Links>
)
