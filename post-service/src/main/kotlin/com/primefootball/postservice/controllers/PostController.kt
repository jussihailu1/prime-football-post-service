package com.primefootball.postservice.controllers

import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.services.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("hello")
    }

    @GetMapping("/user/{id}")
    fun getAllPostsFromUser(@PathVariable id: String): ResponseEntity<List<PostDto>> =
        ResponseEntity.ok(postService.getAllPostsFromUser(id))

    @PostMapping
    fun createPost(@RequestBody post: PostDto): ResponseEntity<PostDto> = ResponseEntity.ok(postService.store(post))

    @PostMapping("/dummy")
    fun dummyData(): ResponseEntity<List<PostDto>> {
        val storedPosts = emptyList<PostDto>().toMutableList()

        for (i in 1..10) {
            for (j in 1..3) storedPosts += postService.store(
                PostDto(
                    null,
                    "Lorem ipsum dolor sit amet.",
                    "user$i",
                    "some file",
                    "some timestamp"
                )
            )
        }

        return ResponseEntity.ok(storedPosts)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Unit> = ResponseEntity.ok(postService.delete(id))

}
