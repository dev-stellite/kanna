package com.shift.kanna.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.shift.kanna.config.security.JsonEmailPasswordAuthenticationFilter
import jakarta.servlet.DispatcherType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.session.web.http.CookieSerializer
import org.springframework.session.web.http.DefaultCookieSerializer

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val loginService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) {
    @Bean
    fun authenticationManager(): AuthenticationManager = ProviderManager(DaoAuthenticationProvider().apply {
        setPasswordEncoder(passwordEncoder)
        setUserDetailsService(loginService)
    })
    @Bean
    fun authenticationFilter(authenticationManager: AuthenticationManager): JsonEmailPasswordAuthenticationFilter {
        val authenticationFilter = JsonEmailPasswordAuthenticationFilter(ObjectMapper())
        authenticationFilter.setAuthenticationManager(authenticationManager)
        
        val contextRepository = HttpSessionSecurityContextRepository()
        authenticationFilter.setSecurityContextRepository(contextRepository)
        
        authenticationFilter.setAuthenticationSuccessHandler { _, _, _ -> }

        return authenticationFilter
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManager = authenticationManager()
        val authenticationFilter = authenticationFilter(authenticationManager)

        http.authorizeHttpRequests {
                it
                    .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                    .requestMatchers(
                        HttpMethod.POST,
                        "/auth/login",
                        "/auth/register",
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .httpBasic(Customizer.withDefaults())
            .csrf { it.disable() }
            .cors {}
            .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}