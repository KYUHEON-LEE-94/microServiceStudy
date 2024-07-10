package com.study.orderservice.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
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
    @LoadBalanced
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
            .baseUrl("http://inventory-service") // 실제 Inventory 서비스의 URL로 설정
    }
}