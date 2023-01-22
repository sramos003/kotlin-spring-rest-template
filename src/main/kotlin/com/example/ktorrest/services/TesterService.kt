package com.example.ktorrest.services

import com.example.ktorrest.models.TesterDetails
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class TesterService(private var jdbcTemplate: JdbcTemplate) {
    init {
        TesterDetails.createTable(jdbcTemplate)
    }
    fun addTestRecordsToTable(nRecords: Long) {
        var ONE_HUNDRED = 100
        var x = 0;
        while (x < nRecords) {
            val id: Long = (ONE_HUNDRED + x).toLong()
            val details = TesterDetails(id, "hello-world", "hello-world-description")
            TesterDetails.insertIntoTester(details, jdbcTemplate)
            x++
        }
    }
}