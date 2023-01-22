package com.example.ktorrest.models

import lombok.Data
import lombok.Getter
import lombok.Setter
import org.springframework.jdbc.core.JdbcTemplate
import java.sql.PreparedStatement

class TesterDetails {
    @Getter
    @Setter
    private val userId: Long = 0L;
    @Getter
    @Setter
    private val userText: String? = null
    @Getter
    @Setter
    private val userDescription: String? = null

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