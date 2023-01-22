package com.example.ktorrest.controllers

import com.example.ktorrest.services.TesterService
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

    @GetMapping(value = ["/add-new-item"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun insertRouteTester(): ResponseEntity<String> {
        testerService.addTestRecordsToTable(50)
        return ResponseEntity.ok(String.format("Inserted %s records into internal db!", "50"))
    }

    @GetMapping(value = ["/get-added-item"], produces = [MediaType.TEXT_HTML_VALUE])
    fun getRouteTester(itemId: Long): ResponseEntity<String> {
        return ResponseEntity.ok("")
    }
}