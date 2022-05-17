package com.primefootball.postservice.services

import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.dtos.PostForTimelineDto
import com.primefootball.postservice.dtos.toPost
import com.primefootball.postservice.models.Post
import com.primefootball.postservice.models.toPostDto
import com.primefootball.postservice.models.toPostForTimelineDto
import com.primefootball.postservice.repositories.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {

    private var tempUsersList =
        listOf("user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9", "user10")

    fun getPostsFromFollowedPeople(requesterId: String): List<PostForTimelineDto> {
        val allPosts = emptyList<Post>().toMutableList()
        for (id in retrieveFollowedUsers(requesterId)) allPosts += postRepository.findAllByPosterId(id)
        return allPosts.map { p -> p.toPostForTimelineDto() }
    }

    // Get list of id's using $followerId. These are from the user's this person follows.
    // For now, this method returns 3 random users
    private fun retrieveFollowedUsers(requesterId: String): List<String> =
        tempUsersList.filter { id -> id != requesterId }.asSequence().shuffled().take(3).toList()

    fun store(postDto: PostDto): PostDto = postRepository.save(postDto.toPost()).toPostDto()

    fun delete(id: Long) = postRepository.deleteById(id)

    fun getAllPostsFromUser(id: String): List<PostDto>? = postRepository.findAllByPosterId(id).map { p -> p.toPostDto() }

}

