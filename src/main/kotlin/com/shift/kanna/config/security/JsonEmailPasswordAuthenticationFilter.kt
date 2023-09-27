package com.shift.kanna.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets

class JsonEmailPasswordAuthenticationFilter(
    private val objectMapper: ObjectMapper,
) : AbstractAuthenticationProcessingFilter(
    AntPathRequestMatcher("/auth/login", "POST")
) {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        if (request?.contentType != "application/json") {
            throw AuthenticationServiceException("Authentication Content-Type not supported: ${request?.contentType}")
        }

        val body = StreamUtils.copyToString(request.inputStream, StandardCharsets.UTF_8)
        val map = objectMapper.readValue(body, Map::class.java)

        val authRequest = UsernamePasswordAuthenticationToken(
            map["email"],
            map["password"],
        )

        return this.authenticationManager.authenticate(authRequest)
    }
}