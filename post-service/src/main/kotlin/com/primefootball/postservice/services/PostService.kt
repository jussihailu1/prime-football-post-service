package com.primefootball.postservice.services

import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.dtos.PostForTimelineDto
import com.primefootball.postservice.dtos.toPost
import com.primefootball.postservice.models.Post
import com.primefootball.postservice.models.toPostDto
import com.primefootball.postservice.models.toPostForTimelineDto
import com.primefootball.postservice.repositories.PostRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(private val postRepository: PostRepository) {

    val tempUsersList =
        listOf(
            UUID.fromString("bcaf9ad0-7d5b-4941-9f0a-5ce844ea4061"),
            UUID.fromString("4abbfcff-f7b6-4cbd-81f2-ce3eb0171f32"),
            UUID.fromString("195fbb30-1858-45ec-9514-f0431d78d479"),
            UUID.fromString("74559a2d-3a04-4a77-8a21-e128b5ca1b0b"),
            UUID.fromString("2c40777f-6a7c-4a9e-8b04-17061c3aecb0"),
            UUID.fromString("3ddc3acc-c7cb-410f-a550-33a019cd5fbd"),
            UUID.fromString("ddf3ff59-283a-45f9-8f4b-e2166108042c"),
            UUID.fromString("71ddfa56-c3a9-4a4f-9ca9-9053521c26af"),
            UUID.fromString("f4cc354f-cefd-4c33-bd88-656e1f586852"),
            UUID.fromString("a4c7768c-4278-4c8e-bce2-dd700b907969")
        )

    fun getPostsFromFollowedPeople(requesterId: UUID): List<PostForTimelineDto> {
        val allPosts = emptyList<Post>().toMutableList()
        for (id in retrieveFollowedUsers(requesterId)) allPosts += postRepository.findAllByPosterId(id)
        return allPosts.map { p -> p.toPostForTimelineDto() }
    }

    // Get list of id's using $followerId. These are from the user's this person follows.
    // For now, this method returns 3 random users
    private fun retrieveFollowedUsers(requesterId: UUID): List<UUID> =
        tempUsersList.filter { id -> id != requesterId }.asSequence().shuffled().take(3).toList()

    fun store(postDto: PostDto): PostDto = postRepository.save(postDto.toPost()).toPostDto()

    fun delete(id: UUID) = postRepository.deleteById(id)

    fun getAllPostsFromUser(id: UUID): List<PostDto>? =
        postRepository.findAllByPosterId(id).map { p -> p.toPostDto() }

}

