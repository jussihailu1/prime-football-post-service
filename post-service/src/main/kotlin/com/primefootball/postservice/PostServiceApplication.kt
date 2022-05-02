package com.primefootball.postservice

import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import java.io.FileInputStream;
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.IOException


@SpringBootApplication
class PostServiceApplication

fun main(args: Array<String>) {
    val jsonPath =
        "D:/Fontys/GitHub/S6/prime-football-post-service/post-service/src/main/kotlin/com/primefootball/postservice/service-account-file.json"

    @Throws(IOException::class)
    fun authExplicit() {
        // You can specify a credential file by providing a path to GoogleCredentials.
        // Otherwise, credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
        val credentials: GoogleCredentials = GoogleCredentials.fromStream(FileInputStream(jsonPath))
            .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"))
        val storage: Storage = StorageOptions.newBuilder().setCredentials(credentials).build().service
        println("Buckets:")
        val buckets: Page<Bucket> = storage.list()
        for (bucket in buckets.iterateAll()) {
            println(bucket.toString())
        }

        val options = FirebaseOptions.builder()
            .setCredentials(credentials)
            .setDatabaseUrl("https://prime-football-default-rtdb.europe-west1.firebasedatabase.app/")
            .build()

        FirebaseApp.initializeApp(options)
    }

    runApplication<PostServiceApplication>(*args)
    authExplicit()
}
