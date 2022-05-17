package com.primefootball.postservice.models

import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.dtos.PostForTimelineDto
import com.primefootball.postservice.dtos.UserDto
import javax.persistence.*

@Entity
open class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    open var text: String,
    open var posterId: String,
    open var file: String,
    open var timestamp: String? = null
)

fun Post.toPostDto() = PostDto(
    id,
    text,
    posterId,
    file,
    timestamp
)

fun Post.toPostForTimelineDto() = PostForTimelineDto(
    id.toString(),
    text,
    UserDto(posterId),
    file,
    timestamp
)
