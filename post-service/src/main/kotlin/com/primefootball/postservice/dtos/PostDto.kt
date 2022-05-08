package com.primefootball.postservice.dtos

import com.primefootball.postservice.models.Post
import java.io.Serializable

data class PostDto(
    open val id: String? = null,
    open val text: String,
    open val posterId: String,
    open var file: String,
    open var timestamp: String? = null
) : Serializable

fun PostDto.toPost() = Post(
    id?.toLong(),
    text,
    posterId,
    file,
    timestamp
)
