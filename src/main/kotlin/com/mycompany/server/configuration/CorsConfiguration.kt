package com.mycompany.server.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration(private val webConfiguration: WebConfiguration) {

    @Bean
    fun configCors(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedMethods(
                        HttpMethod.HEAD.name,
                        HttpMethod.GET.name,
                        HttpMethod.POST.name,
                        HttpMethod.PUT.name,
                        HttpMethod.DELETE.name,
                        HttpMethod.PATCH.name
                    )
                    .allowedOriginPatterns(*webConfiguration.cors.allowedOrigins)
                    .allowCredentials(true)
            }


        }
    }
}