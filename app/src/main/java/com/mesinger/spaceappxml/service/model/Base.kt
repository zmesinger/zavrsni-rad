package com.mesinger.spaceappxml.service.model

import com.google.gson.annotations.SerializedName

data class Base (
    @SerializedName("collection")
    val collection : Collection
    )