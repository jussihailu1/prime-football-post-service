package com.primefootball.postservice

import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.auth.oauth2.GoogleCredentials
import com.google.common.collect.Lists
import java.io.FileInputStream
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.IOException


@SpringBootApplication
class PostServiceApplication

fun main(args: Array<String>) {
    runApplication<PostServiceApplication>(*args)
}
