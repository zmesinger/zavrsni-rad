package com.mesinger.spaceappxml.service.model

data class Post(
    var postID: String= "",
    val title: String = "",
    val description: String = "",
    val imageURL: String = "",
    val userEmail: String = ""
)
