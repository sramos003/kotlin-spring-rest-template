package com.example.kotlinspring.services

import com.example.kotlinspring.models.TesterDetails
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.PreparedStatement
import java.sql.ResultSet

@Component
class TesterService(private var jdbcTemplate: JdbcTemplate) {

    init {
        resetTable()
        createTable()
        addTestRecordsToTable(30L)
    }

    private final fun addTestRecordsToTable(nRecords: Long) {
        val oneHundred = 100
        var x = 0L
        while (x < nRecords) {
            val id: Long = oneHundred + x
            val details = TesterDetails(id, "hello-world", "hello-world-description")
            insertIntoTester(details)
            x++
        }
    }

    @Throws(DataAccessException::class)
    fun insertIntoTester(details: TesterDetails) {
        val rowsUpdated = jdbcTemplate.update(
            "INSERT INTO TESTER (USER_ID, USER_TEXT, USER_DESCRIPTION) VALUES (?, ?, ?)"
        ) { ps: PreparedStatement ->
            ps.setLong(1, details.userId)
            ps.setString(2, details.userText)
            ps.setString(3, details.userDescription)

        }
        println(String.format("%s ROWS UPDATED", rowsUpdated))
    }

    @Throws(DataAccessException::class)
    fun getTableRecords(): List<TesterDetails> {
        return jdbcTemplate.query(
            "SELECT * FROM TESTER"
        ) { rs: ResultSet, rowNum: Int ->
            TesterDetails(
                rs.getLong("USER_ID"),
                rs.getString("USER_TEXT"),
                rs.getString("USER_DESCRIPTION")
            )
        }
    }

    @Throws(DataAccessException::class)
    final fun createTable() {
        jdbcTemplate.update(
            "CREATE TABLE TESTER(" +
                    "USER_ID BIGINT NOT NULL PRIMARY KEY," +
                    " USER_TEXT VARCHAR(50) NOT NULL," +
                    " USER_DESCRIPTION VARCHAR(100) NOT NULL DEFAULT 'GENERIC DESCRIPTION'" +
                    " )"
        )
    }

    @Throws(DataAccessException::class)
    final fun resetTable() {
        jdbcTemplate.update("DROP TABLE if EXISTS TESTER")
    }
}