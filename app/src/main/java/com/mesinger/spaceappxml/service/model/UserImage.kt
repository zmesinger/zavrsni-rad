package com.mesinger.spaceappxml.service.model

import android.net.Uri

data class UserImage(
    val title: String,
    val description: String,
    val imageUri: Uri,
    val username: String
)
