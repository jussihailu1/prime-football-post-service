package com.primefootball.postservice.dtos

import java.io.Serializable

data class UserDto(
    val id: String
    // More stuff like username and profile picture
) : Serializable
