package com.primefootball.postservice

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@SpringBootTest
class PostServiceApplicationTests {

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

    @Test
    fun contextLoads(){

    }
}
