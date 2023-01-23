package com.example.kotlinspring.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("POST", "PUT", "PATCH", "GET", "DELETE")
            .allowedHeaders(
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Headers",
                "Content-Type",
                "Cookie",
                "Authorization",
            )
        super.addCorsMappings(registry)
    }
}