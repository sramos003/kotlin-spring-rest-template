package com.example.kotlinspring.services

import com.example.kotlinspring.models.TesterDetails
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementSetter
import org.springframework.jdbc.core.RowMapper
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
        val sql = "INSERT INTO TESTER (USER_TEXT, USER_DESCRIPTION) VALUES (?, ?)"
        val statementSetter = PreparedStatementSetter { ps: PreparedStatement ->
            run {
                ps.setString(1, details.userText)
                ps.setString(2, details.userDescription)
            }
        }
        jdbcTemplate.update(sql, statementSetter)
    }

    @Throws(DataAccessException::class)
    fun getTableRecords(): List<TesterDetails> {
        val sql = "SELECT USER_ID, USER_TEXT, USER_DESCRIPTION FROM TESTER"
        val mapper: RowMapper<TesterDetails> =
            RowMapper { rs: ResultSet, rowNum: Int ->
                run {
                    TesterDetails(
                        rs.getLong("USER_ID"),
                        rs.getString("USER_TEXT"),
                        rs.getString("USER_DESCRIPTION")
                    )
                }
            }
        return jdbcTemplate.query(sql, mapper)
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