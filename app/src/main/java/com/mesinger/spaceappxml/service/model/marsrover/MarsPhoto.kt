package com.mesinger.spaceappxml.service.model.marsrover

import com.google.gson.annotations.SerializedName

data class MarsPhoto(
    @SerializedName("photos")
    val photos: List<Photo>
)