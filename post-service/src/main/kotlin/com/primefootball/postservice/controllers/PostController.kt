package com.primefootball.postservice.controllers

import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.dtos.PostForTimelineDto
import com.primefootball.postservice.services.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/posts")
class PostController(private val postService: PostService) {

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long): ResponseEntity<PostDto> {
        val post = postService.getOne(id)
        return ResponseEntity.ok(post)
    }

    @GetMapping
    fun getAllPosts(): ResponseEntity<List<PostDto>> {
        return ResponseEntity.ok(postService.getAll())
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<List<PostForTimelineDto>> {
        val posts = postService.getPostsFromFollowedPeople("user1")
        return ResponseEntity.ok(posts)
    }

    @PostMapping
    fun createPost(@RequestBody post: PostDto): ResponseEntity<PostDto> {
        return ResponseEntity.ok(postService.store(post))
    }

    @PostMapping("/dummy")
    fun dummyData(): ResponseEntity<List<PostDto>> {
        var dummyPosts = listOf(
            PostDto(null, "Lorem ipsum dolor sit amet.", "user1", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user1", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user2", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user2", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user3", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user3", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user4", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user4", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user5", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user5", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user6", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user6", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user7", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user7", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user8", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user8", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user9", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user9", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user10", "some file", "some timestamp"),
            PostDto(null, "Lorem ipsum dolor sit amet.", "user10", "some file", "some timestamp"),
        )

        for (dp in dummyPosts){
            postService.store(dp)
        }

        return ResponseEntity.ok(dummyPosts)
    }

    @PutMapping
    fun updatePost(@RequestBody post: PostDto): ResponseEntity<PostDto> {
        return ResponseEntity.ok(postService.update(post))
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Unit> {
        postService.delete(id)
        return ResponseEntity.ok(Unit)
    }
}
