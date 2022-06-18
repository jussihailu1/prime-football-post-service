package com.primefootball.postservice.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello-posts")
class RootController() {
    @GetMapping()
    fun root(): String = "Prime Football Posts-service"
}
