package com.mycompany.server.configuration;

import org.springframework.web.filter.CorsFilter
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class CorsConfiguration {

    @Bean
    fun configCors(): CorsFilter {
        val corsConfig = CorsConfiguration()
            .applyPermitDefaultValues().apply {
                allowedMethods = listOf(
                    HttpMethod.HEAD.name,
                    HttpMethod.GET.name,
                    HttpMethod.POST.name,
                    HttpMethod.PUT.name,
                    HttpMethod.DELETE.name,
                    HttpMethod.PATCH.name
                )
                allowedOrigins = listOf("http://localhost:8080")
                allowCredentials = true
                addExposedHeader("Content-Disposition")
            }
        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfig)
        }
        return CorsFilter(source)
    }
}