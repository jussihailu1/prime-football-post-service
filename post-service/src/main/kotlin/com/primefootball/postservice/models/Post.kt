package com.primefootball.postservice.models

import com.primefootball.postservice.dtos.PostDto
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "post")
open class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    open var text: String? = null
)

fun Post.toPostDTO() = PostDto(
    id = id.toString(),
    text = text
)
