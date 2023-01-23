package com.example.kotlinspring

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@OpenAPIDefinition(
    info = Info(
        title = "Test Service",
        version = "1.0",
        description = "REST API to service This Tester Application"
    ), servers = [Server(url = "/")]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
