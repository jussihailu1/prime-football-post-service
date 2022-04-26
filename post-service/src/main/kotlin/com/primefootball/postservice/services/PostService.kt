package com.primefootball.postservice.services

import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.dtos.toPost
import com.primefootball.postservice.models.toPostDTO
import com.primefootball.postservice.repositories.PostRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PostService(private val postRepository: PostRepository) {

    fun getAll(): List<PostDto> {
        return postRepository.findAll().map { p -> p.toPostDTO() }
    }

    fun getOne(id: Long): PostDto {
        return postRepository.findById(id).get().toPostDTO()
    }

    fun store(postDto: PostDto) {
        postRepository.save(postDto.toPost())
    }

    fun update(postDto: PostDto) {
        postRepository.save(postDto.toPost())
    }

    fun delete(id: Long) {
        postRepository.deleteById(id)
    }
}
