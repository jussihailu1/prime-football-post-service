package com.primefootball.postservice.dtos

import java.io.Serializable

data class PostForTimelineDto(
    val id: String? = null,
    val text: String,
    val user: UserDto,
    var file: String,
    var timestamp: String? = null
) : Serializable
