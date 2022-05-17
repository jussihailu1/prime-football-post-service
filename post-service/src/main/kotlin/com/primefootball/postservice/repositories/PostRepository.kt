package com.primefootball.postservice.repositories

import com.primefootball.postservice.models.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findAllByPosterId(id: String): List<Post>
}
