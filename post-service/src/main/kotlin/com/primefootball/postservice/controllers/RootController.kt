package com.primefootball.postservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/hello-posts")
class RootController() {
    @GetMapping()
    fun getTimeline(@PathVariable requesterId: UUID): String = "Prime Football Posts-service"
}
