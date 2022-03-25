package com.mesinger.spaceappxml.service.model

import com.google.gson.annotations.SerializedName

data class PictureOfTheDay(
    @SerializedName("id")
    val id: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("hdurl")
    val hdurl: String,
    @SerializedName("media_type")
    val media_type: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)