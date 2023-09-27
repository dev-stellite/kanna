package com.shift.kanna.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class GeneralConfig {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}