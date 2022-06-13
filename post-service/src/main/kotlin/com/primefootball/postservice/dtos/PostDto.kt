package com.primefootball.postservice.dtos

import com.primefootball.postservice.models.Post
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class PostDto(
    val id: UUID? = null,
    val text: String,
    val posterId: UUID,
    var file: String,
    var timestamp: String
) : Serializable

fun PostDto.toPost() = Post(
    id,
    text,
    posterId,
    file,
    timestamp!!
)
