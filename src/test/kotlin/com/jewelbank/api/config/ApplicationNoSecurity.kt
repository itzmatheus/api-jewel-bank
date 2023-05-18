package com.jewelbank.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@Profile("nosecurity")
class ApplicationNoSecurity {

    //TODO: Change this implementation in the future to use only in test

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
            .authorizeHttpRequests().requestMatchers("/**").permitAll()
            .anyRequest().authenticated()
        return http.build()
    }
}