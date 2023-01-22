package com.example.ktorrest.controllers

import com.example.ktorrest.models.TesterDetails
import lombok.AllArgsConstructor
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/test-controller"])
@AllArgsConstructor
class TestController(
    private var jdbcTemplate: JdbcTemplate
) {
    @GetMapping(value = ["/one"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun testRouteOne(): ResponseEntity<String> {
        return ResponseEntity.ok("My first kotlin API")
    }

    @GetMapping(value = ["/add-new-item"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun testRouteInsert(): ResponseEntity<String> {
        val details = TesterDetails(123L, "hello-world", "hello-world-description")
        TesterDetails.createTable(jdbcTemplate)
        TesterDetails.insertIntoTester(details, jdbcTemplate)
        return ResponseEntity.ok("Inserted a record into internal db!")
    }
}