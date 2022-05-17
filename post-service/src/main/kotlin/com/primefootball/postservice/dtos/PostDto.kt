package com.primefootball.postservice.dtos

import com.primefootball.postservice.models.Post
import java.io.Serializable

data class PostDto(
    val id: Long? = null,
    val text: String,
    val posterId: String,
    var file: String,
    var timestamp: String? = null
) : Serializable

fun PostDto.toPost() = Post(
    id,
    text,
    posterId,
    file,
    timestamp
)
