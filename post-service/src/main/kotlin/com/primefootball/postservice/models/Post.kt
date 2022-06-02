package com.primefootball.postservice.models

import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.dtos.PostForTimelineDto
import com.primefootball.postservice.dtos.UserDto
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
open class Post(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    open var id: UUID? = UUID.randomUUID(),
    open var text: String,
    @Type(type = "org.hibernate.type.UUIDCharType")
    open var posterId: UUID,
    open var file: String,
    open var timestamp: String
)

fun Post.toPostDto() = PostDto(
    id,
    text,
    posterId,
    file,
    timestamp
)

fun Post.toPostForTimelineDto() = PostForTimelineDto(
    id!!,
    text,
    UserDto(posterId),
    file,
    timestamp
)
