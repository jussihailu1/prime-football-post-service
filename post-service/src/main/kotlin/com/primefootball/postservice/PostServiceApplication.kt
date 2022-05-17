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
    val jsonPath = "service-account-file.json"

    @Throws(IOException::class)
    fun authExplicit() {
        val credentials: GoogleCredentials = GoogleCredentials.fromStream(FileInputStream(jsonPath))
            .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"))

        val options = FirebaseOptions.builder()
            .setCredentials(credentials)
            .setDatabaseUrl("https://prime-football-default-rtdb.europe-west1.firebasedatabase.app/")
            .build()

        FirebaseApp.initializeApp(options)
    }

    runApplication<PostServiceApplication>(*args)
    authExplicit()
}
