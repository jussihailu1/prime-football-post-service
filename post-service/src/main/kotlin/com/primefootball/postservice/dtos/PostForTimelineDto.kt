package com.primefootball.postservice.dtos

import java.io.Serializable
import java.util.*

data class PostForTimelineDto(
    val id: UUID,
    val text: String,
    val user: UserDto,
    var file: String,
    var timestamp: String
) : Serializable
