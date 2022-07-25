package com.mesinger.spaceappxml.service.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("album")
    val album : List<String>,
    @SerializedName("center")
    val center : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("photographer")
    val photographer : String,
    @SerializedName("keywords")
    val keywords : List<String>,
    @SerializedName("location")
    val location : String,
    @SerializedName("nasa_id")
    val nasa_id : String,
    @SerializedName("media_type")
    val media_type : String,
    @SerializedName("date_created")
    val date_created : String,
    @SerializedName("description")
    val description : String
)
