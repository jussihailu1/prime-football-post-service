package com.primefootball.postservice.controllers

import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.models.toPostDTO
import com.primefootball.postservice.services.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/posts")
class PostController (private val postService: PostService) {

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long): ResponseEntity<PostDto> {
        val post = postService.getOne(id)
        return ResponseEntity<PostDto>(post, HttpStatus.OK)
    }

    @GetMapping
    fun getAllPosts(): ResponseEntity<List<PostDto>> {
        val posts = postService.getAll()
        return ResponseEntity<List<PostDto>>(posts, HttpStatus.OK)
    }

    @PostMapping
    fun createPost(@RequestBody post: PostDto): ResponseEntity<Unit> {
        postService.store(post)
        return ResponseEntity<Unit>(Unit, HttpStatus.OK)
    }

    @PutMapping
    fun updatePost(
        @RequestBody post: PostDto
    ): ResponseEntity<Unit> {
        postService.update(post)
        return ResponseEntity<Unit>(Unit, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Unit> {
        postService.delete(id)
        return ResponseEntity<Unit>(Unit, HttpStatus.OK)
    }
}
