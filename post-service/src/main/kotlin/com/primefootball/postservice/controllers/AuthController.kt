package com.primefootball.postservice.controllers


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController {

    @GetMapping("/email/{email}")
    fun getByEmail(@PathVariable email: String): UserRecord = FirebaseAuth.getInstance().getUserByEmail(email)

    @GetMapping("/phone-number/{phoneNumber}")
    fun getByPhoneNumber(@PathVariable phoneNumber: String): UserRecord =
        FirebaseAuth.getInstance().getUserByPhoneNumber(phoneNumber)

    @GetMapping("/uid/{uid}")
    fun getByUid(@PathVariable uid: String): UserRecord = FirebaseAuth.getInstance().getUser(uid)

    @GetMapping("/custom-jwt/{uid}")
    fun generateJWT(@PathVariable uid: String): String = FirebaseAuth.getInstance().createCustomToken(uid)

    @GetMapping("uid/from-id-token/{idToken}")
    fun uidFromIdToken(@PathVariable idToken: String): String = FirebaseAuth.getInstance().verifyIdToken(idToken).uid

}
