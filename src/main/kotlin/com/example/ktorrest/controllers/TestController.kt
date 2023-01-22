package com.example.ktorrest.controllers

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/test-controller"])
class TestController {
    
    // empty constructor
    fun TestController() {
    }

    @GetMapping(value = ["/one"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun testRouteOne(): ResponseEntity<String> {
        return ResponseEntity.ok("My first kotlin API")
    }

}