package com.mesinger.spaceappxml.service.model.nasalibrary

import com.google.gson.annotations.SerializedName

data class Collection(
    //@SerializedName("version")
    //val version : Double,
    @SerializedName("href")
    val href : String,
    @SerializedName("items")
    val items : List<Item>,
    //@SerializedName("metadata")
    //val metadata : Metadata,
    @SerializedName("links")
    val links : List<Link>
)
