package com.example.ktorrest.models

import org.springframework.jdbc.core.JdbcTemplate
import java.sql.PreparedStatement

class TesterDetails(
    private var userId: Long,
    private var userText: String?,
    private var userDescription: String?
) {

    companion object {
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
    }
}