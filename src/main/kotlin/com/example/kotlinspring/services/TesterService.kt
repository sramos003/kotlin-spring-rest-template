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
    }

    @Throws(DataAccessException::class)
    fun insertIntoTable(details: TesterDetails) {
        jdbcTemplate.update(
            "INSERT INTO TESTER (USER_TEXT, USER_DESCRIPTION) VALUES (?, ?)"
        ) { ps: PreparedStatement ->
            ps.setString(1, details.userText)
            ps.setString(2, details.userDescription)

        }
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
                    "USER_ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    " USER_TEXT VARCHAR(500) NOT NULL," +
                    " USER_DESCRIPTION VARCHAR(500) NOT NULL DEFAULT 'GENERIC DESCRIPTION'" +
                    " )"
        )
    }

    @Throws(DataAccessException::class)
    final fun resetTable() {
        jdbcTemplate.update("DROP TABLE if EXISTS TESTER")
    }
}