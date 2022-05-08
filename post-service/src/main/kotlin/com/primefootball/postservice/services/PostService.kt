package com.primefootball.postservice.services

import com.google.rpc.DebugInfo
import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.dtos.PostForTimelineDto
import com.primefootball.postservice.dtos.toPost
import com.primefootball.postservice.models.Post
import com.primefootball.postservice.models.toPostDto
import com.primefootball.postservice.models.toPostForTimelineDto
import com.primefootball.postservice.repositories.PostRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class PostService(private val postRepository: PostRepository) {
    var tempUsersList =
        listOf("user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9", "user10")

    fun getPostsFromFollowedPeople(requesterId: String): List<PostForTimelineDto> {
        var allPosts = emptyList<Post>()
        for (id in retrieveFollowedUsers(requesterId)) allPosts += postRepository.findAllByPosterId(id)
        return allPosts.map { p -> p.toPostForTimelineDto() }
    }

    private fun retrieveFollowedUsers(requesterId: String): List<String> {
        // Get list of id's using $followerId. These are from the user's this person follows.
        // For now, this method returns 3 random users
        return tempUsersList.filter { id -> id != requesterId }.asSequence().shuffled().take(3).toList()
    }

    fun getAll(): List<PostDto> {
        return postRepository.findAll().map { p -> p.toPostDto() }
    }

    fun getOne(id: Long): PostDto {
        return postRepository.findById(id).get().toPostDto()
    }

    fun store(postDto: PostDto): PostDto {
        return postRepository.save(postDto.toPost()).toPostDto()
    }

    fun update(postDto: PostDto): PostDto {
        return postRepository.save(postDto.toPost()).toPostDto()
    }

    fun delete(id: Long) {
        postRepository.deleteById(id)
    }
}
