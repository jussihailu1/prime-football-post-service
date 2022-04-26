package com.primefootball.postservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PostServiceApplication

fun main(args: Array<String>) {
	runApplication<PostServiceApplication>(*args)
}
