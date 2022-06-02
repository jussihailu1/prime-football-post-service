package com.primefootball.postservice.dtos

import java.io.Serializable
import java.util.UUID

data class UserDto(
    val id: UUID
    // More stuff like username and profile picture
) : Serializable
