package com.jewelbank.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer

@Configuration
@Profile("test")
class ApplicationNoSecurity {

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer{
            it.ignoring().requestMatchers("/**")
        }
    }

}