package com.primefootball.postservice.controllers

import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.services.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    @GetMapping("/user/{id}")
    fun getAllPostsFromUser(@PathVariable id: UUID): ResponseEntity<List<PostDto>> =
        ResponseEntity.ok(postService.getAllPostsFromUser(id))

    @PostMapping
    fun createPost(@RequestBody post: PostDto): ResponseEntity<PostDto> = ResponseEntity.ok(postService.store(post))

    @PostMapping("/dummy")
    fun dummyData(): ResponseEntity<List<PostDto>> {
        val storedPosts = emptyList<PostDto>().toMutableList()

        for (i in 0..9) for (j in 1..3) storedPosts += postService.store(
            PostDto(
                text = "Lorem ipsum dolor sit amet.",
                posterId = postService.tempUsersList[i],
                file = "some file",
                timestamp = "some timestamp"
            )
        )

        return ResponseEntity.ok(storedPosts)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: UUID): ResponseEntity<Unit> = ResponseEntity.ok(postService.delete(id))

}
