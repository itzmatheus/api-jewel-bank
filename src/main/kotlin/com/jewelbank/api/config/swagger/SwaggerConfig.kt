package com.jewelbank.api.config.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class SwaggerConfig {

    @Value("#{ @environment['swagger.server-url'] }")
    private val serverUrl: String = "/"

    @Bean
    fun springOpenAPI(): OpenAPI? {
        return OpenAPI()
            .components(Components()
                .addSecuritySchemes("bearer-key",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
            .info(
                Info().title("API Jewel Bank")
                    .description("API of Jewel Bank.")
                    .version("v1.0.0")
                    .license(License().name("Apache 2.0").url("https://springdoc.org"))
            )
            .addSecurityItem(
                SecurityRequirement()
                    .addList("bearer-key", Collections.emptyList())
            )
            .addServersItem(Server().url(serverUrl))
            .externalDocs(
                ExternalDocumentation()
                    .description("Spring Wiki Documentation")
                    .url("https://spring.wiki.github.org/docs")
            )
    }


}