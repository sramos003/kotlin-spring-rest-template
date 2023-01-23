package com.example.kotlinspring.controllers

import com.example.kotlinspring.models.TesterDetails
import com.example.kotlinspring.services.TesterService
import com.thedeanda.lorem.Lorem
import com.thedeanda.lorem.LoremIpsum
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/test-controller"])
class TestController(private var testerService: TesterService) {

    @GetMapping(value = ["/one"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun healthCheckRoute(): ResponseEntity<String> {
        return ResponseEntity.ok("My first kotlin API")
    }
    
    @GetMapping(value = ["/get-all-items"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRouteTester(): ArrayList<TesterDetails> {
        addTestRecordsToTable(30L)
        return ArrayList(testerService.getTableRecords())
    }

    private final fun addTestRecordsToTable(nRecords: Long) {
        var x = 0L
        val ipsum: Lorem = LoremIpsum.getInstance()
        while (x < nRecords) {
            testerService.insertIntoTable(TesterDetails(ipsum.getWords(5), ipsum.getWords(5)))
            x++
        }
    }
}