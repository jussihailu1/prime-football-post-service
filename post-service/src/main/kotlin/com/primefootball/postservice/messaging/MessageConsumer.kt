package com.primefootball.postservice.messaging

import com.google.gson.Gson
import com.primefootball.postservice.services.PostService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import java.util.*

@Component
class MessageConsumer(private val postService: PostService) {
    private val gson = Gson()

    @RabbitListener(queues = [MessagingConfig.RECEIVER_QUEUE])
    fun consumeMessageFromQueue(userId: UUID): String? = gson.toJson(postService.getPostsFromFollowedPeople(userId))
}
