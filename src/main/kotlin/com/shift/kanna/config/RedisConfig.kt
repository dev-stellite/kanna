package com.shift.kanna.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession

@EnableSpringHttpSession
@Configuration
class RedisConfig {
    @Value("\${spring.data.redis.port}")
    private var port: Int = 0

    @Value("\${spring.data.redis.host}")
    private lateinit var host: String

    @Bean
    fun connectionFactory() = LettuceConnectionFactory(host, port)
}