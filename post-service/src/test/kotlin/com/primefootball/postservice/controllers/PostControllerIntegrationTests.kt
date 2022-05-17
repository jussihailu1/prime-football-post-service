package com.primefootball.postservice.controllers

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.web.client.getForEntity
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName


@Testcontainers
@SpringBootTest
internal class PostControllerIntegrationTests @Autowired constructor(
//    private val jdbc: JdbcTemplate
) {

    private val client = TestRestTemplate()

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
    fun `Container is up and running`() {
        assertTrue(container.isRunning)
    }

    @Test
    fun getAllPostsFromUser() {
//        val entity = client.restTemplate.getForEntity<String>("/test")
//        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
//        assertThat(entity.body).contains("hello")
        val response: ResponseEntity<String> =
            client.getForEntity("https://jsonplaceholder.typicode.com/todos/1", String::class.java)

        assertEquals(response.statusCode, HttpStatus.OK)
    }

//    @Test
//    fun createPost() {
//        assertTrue(true)
//    }
//
//    @Test
//    fun dummyData() {
//        assertTrue(true)
//    }
//
//    @Test
//    fun deletePost() {
//        assertTrue(true)
//    }
}
