package com.example.ktorrest.controllers

import lombok.AllArgsConstructor
import lombok.NonNull
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/test-controller"])
@AllArgsConstructor
class TestController {
    @NonNull
    private val jdbcTemplate: JdbcTemplate? = null
    
    @GetMapping(value = ["/one"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun testRouteOne(): ResponseEntity<String> {
        return ResponseEntity.ok("My first kotlin API")
    } 
    
    @GetMapping(value = ["/add-new-item"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun testRouteInsert(): ResponseEntity<String> {
        return ResponseEntity.ok("");
    }
}