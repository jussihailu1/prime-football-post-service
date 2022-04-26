package com.primefootball.postservice.repositories;

import com.primefootball.postservice.models.Post
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository : JpaRepository<Post, Long> {
}
