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
import java.util.concurrent.ThreadLocalRandom

@RestController
@RequestMapping(value = ["/test-controller"])
class TestController(private var testerService: TesterService) {

    @GetMapping(value = ["/health-check"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun healthCheckRoute(): ResponseEntity<String> {
        return ResponseEntity.ok("My first kotlin API")
    }
    
    @GetMapping(value = ["/get-all-items"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRouteTester(): List<TesterDetails> {
        initializeTableData()
        return ArrayList(testerService.getTableRecords())
    }

    private final fun initializeTableData() {
        val ipsum: Lorem = LoremIpsum.getInstance()
        val recordsToGenerate: Long = ThreadLocalRandom.current().nextLong(5, 50)
        var x = 0L
        while (x < recordsToGenerate) {
            testerService.insertIntoTable(TesterDetails(ipsum.getWords(5), ipsum.getWords(5)))
            x++
        }
    }
}