package com.study.discoveryserver.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

/**
 *packageName    : com.study.discoveryserver.config
 * fileName       : SecurityConfig
 * author         : LEE KYUHEON
 * date           : 2024-07-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-14        LEE KYUHEON       최초 생성
 */
@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val authenticationConfiguration: AuthenticationConfiguration,
    @Value("\${eureka.username}") private val username: String,
    @Value("\${eureka.password}") private val password: String
        ){



    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withUsername(username)
            .password(passwordEncoder().encode(password))
            .authorities("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        // NoOpPasswordEncoder is for testing purposes only and should not be used in production
        return NoOpPasswordEncoder.getInstance()
    }

    // Filter Chain
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf{it.disable()}
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .httpBasic{}
        return http.build()
    }
}