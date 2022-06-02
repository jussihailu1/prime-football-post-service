package com.primefootball.postservice.services

import com.primefootball.postservice.dtos.PostDto
import com.primefootball.postservice.repositories.PostRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.from
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@SpringBootTest
internal class PostServiceTests @Autowired constructor(
    private val postService: PostService,
    private val postRepository: PostRepository,
    private val jdbc: JdbcTemplate
) {

    companion object {
        @Container
        val container = MySQLContainer<Nothing>(DockerImageName.parse("mysql"))
            .apply {
                withDatabaseName("prime-football")
                withPassword("root")
            }

        @JvmStatic
        @DynamicPropertySource
        fun dataSourceConfig(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl)
            registry.add("spring.datasource.password", container::getPassword)
        }
    }

    @Test
    fun `Container is up and running` () {
        assertTrue(container.isRunning)
    }

    @AfterEach
    fun tearDown() {
//        jdbc.execute("truncate table posts")
    }

    @Test
    fun getPostsFromFollowedPeople() {
    }

    @Test
    fun store() {
        val postsCountBefore = postRepository.findAll().size
        val postToStore = PostDto(null, "Test post", "test user id", "some file", "some timestamp")

        val storedPost = postService.store(postToStore)

        val postsCountAfter = postRepository.findAll().size
        assertEquals(postsCountBefore + 1, postsCountAfter)
        assertThat(storedPost)
            .returns(postToStore.text, from(PostDto::text))
            .returns(postToStore.posterId, from(PostDto::posterId))
            .returns(postToStore.file, from(PostDto::file))
            .returns(postToStore.timestamp, from(PostDto::timestamp))
    }

    @Test
    fun delete() {
        val postToStoreDto = PostDto(null, "Test post", "test user id", "some file", "some timestamp")
        val storedPost = postService.store(postToStoreDto)
        val postsCountBefore = postRepository.findAll().size

        postService.delete(storedPost.id!!)

        val postsCountAfter = postRepository.findAll().size
        assertEquals(postsCountBefore - 1, postsCountAfter)
        assertTrue(postRepository.findById(storedPost.id!!).isEmpty)
    }

    @Test
    fun getAllPostsFromUser() {
        assertTrue(true)
    }
}
