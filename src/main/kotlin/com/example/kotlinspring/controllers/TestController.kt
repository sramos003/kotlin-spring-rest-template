package com.example.kotlinspring.controllers

import com.example.kotlinspring.models.TesterDetails
import com.example.kotlinspring.services.TesterService
import lombok.AllArgsConstructor
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/test-controller"])
@AllArgsConstructor
class TestController(private var testerService: TesterService) {

    @GetMapping(value = ["/one"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun healthCheckRoute(): ResponseEntity<String> {
        return ResponseEntity.ok("My first kotlin API")
    }
    
    @GetMapping(value = ["/get-all-items"], produces = [MediaType.TEXT_HTML_VALUE])
    fun getRouteTester(): ResponseEntity<String> {
        testerService
            .getTableRecords()
            .stream()
            .forEach { testerDetails: TesterDetails -> println(testerDetails) }
        return ResponseEntity.ok("CHECK LOGS FOR DETAILS")
    }
}