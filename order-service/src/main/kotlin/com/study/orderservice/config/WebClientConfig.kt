package com.study.orderservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

/**
 *packageName    : com.study.orderservice.config
 * fileName       : WebClientConfig
 * author         : LEE KYUHEON
 * date           : 2024-07-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-08        LEE KYUHEON       최초 생성
 */
@Configuration
class WebClientConfig {

    @Bean
    fun webclient():WebClient{
        return WebClient.create()
    }
}