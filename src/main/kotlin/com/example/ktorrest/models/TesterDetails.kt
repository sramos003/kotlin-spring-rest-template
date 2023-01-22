package com.example.ktorrest.models

import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import java.sql.PreparedStatement

class TesterDetails(
    private var userId: Long,
    private var userText: String?,
    private var userDescription: String?
) {

    companion object {
        @Throws(DataAccessException::class)
        fun insertIntoTester(details: TesterDetails, jdbcTemplate: JdbcTemplate) {
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
        fun createTable(jdbcTemplate: JdbcTemplate) {
            val rowsUpdated = jdbcTemplate.update(
                "CREATE TABLE TESTER(" +
                        " USER_ID INT NOT NULL PRIMARY KEY," +
                        " USER_TEXT VARCHAR(50) NOT NULL," +
                        " USER_DESCRIPTION VARCHAR(100) NOT NULL DEFAULT 'GENERIC DESCRIPTION' )"
            )
            println(String.format("%s ROWS UPDATED", rowsUpdated))
        }
    }
}