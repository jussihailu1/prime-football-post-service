package com.primefootball.postservice.dtos

import com.primefootball.postservice.models.Post
import java.io.Serializable
import java.util.*

data class PostDto(val id: String? = null, val text: String? = null) : Serializable

fun PostDto.toPost() = Post(
    id = id?.toLong(),
    text = text
)
