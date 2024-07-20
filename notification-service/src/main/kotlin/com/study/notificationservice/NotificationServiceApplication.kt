package com.study.notificationservice

import com.study.notificationservice.event.OrderPlacedEvent
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.KafkaListener

private val logger = KotlinLogging.logger{}

@SpringBootApplication
class NotificationServiceApplication{
    @KafkaListener(topics = ["notificationTopic"])
    fun handleNotification(orderPlacedEvent:OrderPlacedEvent){
        //send out an email notification

        logger.info{"Received Notification for Order ${orderPlacedEvent.orderNumber}"}
    }

}

fun main(args: Array<String>) {
    runApplication<NotificationServiceApplication>(*args)
}
