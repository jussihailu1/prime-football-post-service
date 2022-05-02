package com.primefootball.postservice.controllers


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/v1/auth")
class AuthController () {

    // There is no log in controller function because that happen

    @GetMapping("/email/{email}")
    fun getByEmail(@PathVariable email: String): UserRecord {
        val userRecord = FirebaseAuth.getInstance().getUserByEmail(email)
        println("Successfully fetched user data: ${userRecord.email}")
        return userRecord
    }

    @GetMapping("/phone-number/{phoneNumber}")
    fun getByPhoneNumber(@PathVariable phoneNumber: String): UserRecord {
        val userRecord = FirebaseAuth.getInstance().getUserByPhoneNumber(phoneNumber)
        println("Successfully fetched user data: ${userRecord.phoneNumber}")
        return userRecord
    }

    @GetMapping("/uid/{uid}")
    fun getByUid(@PathVariable uid: String): UserRecord {
        val userRecord = FirebaseAuth.getInstance().getUser(uid)
        println("Successfully fetched user data: ${userRecord.uid}")
        return userRecord
    }

    @GetMapping("/custom-jwt/{uid}")
    fun generateJWT(@PathVariable uid: String): String { // Not using this
        return FirebaseAuth.getInstance().createCustomToken(uid)
    }

    @GetMapping("uid/from-id-token/{idToken}")
    fun uidFromIdToken(@PathVariable idToken: String): String {
        val decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken)
        return decodedToken.uid
    }
}
