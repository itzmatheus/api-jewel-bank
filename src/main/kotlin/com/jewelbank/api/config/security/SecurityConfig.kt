package com.jewelbank.api.config.security

import com.jewelbank.api.config.security.jwt.JwtAuthenticationFilter
import com.jewelbank.api.config.security.jwt.JwtAuthorizationFilter
import com.jewelbank.api.config.security.jwt.JwtTokenUtil
import com.jewelbank.api.config.security.service.UserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
) {
    private val jwtToken = JwtTokenUtil()
    private fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.userDetailsService(userDetailsService)
        return authenticationManagerBuilder.build()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManager = authManager(http)
        http.authorizeHttpRequests().requestMatchers(
            "/bankuser/register", "/swagger-ui/**", "/swagger-resources", "/swagger-resources/**",  "/configuration/ui",
            "/configuration/security", "/swagger-ui.html", "/webjars/**", "/v3/api-docs", "/v3/api-docs/**",
            "/swagger-ui/**", "/docs", "/docs/**",
        ).permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .authenticationManager(authenticationManager)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilter(JwtAuthenticationFilter(jwtToken, authenticationManager))
            .addFilter(JwtAuthorizationFilter(jwtToken, userDetailsService, authenticationManager))
        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}