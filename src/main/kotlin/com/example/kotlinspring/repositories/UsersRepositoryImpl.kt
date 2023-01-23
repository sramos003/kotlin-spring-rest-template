package com.example.kotlinspring.repositories

import com.example.kotlinspring.models.TesterDetails
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementSetter
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.PreparedStatement
import java.sql.ResultSet

@Component

class UsersRepositoryImpl(private var jdbcTemplate: JdbcTemplate): IUsersRepository {
    val usersRowMapper: RowMapper<TesterDetails> =
        RowMapper { rs: ResultSet, rowNum: Int ->
            run {
                TesterDetails(
                    rs.getLong("USER_ID"),
                    rs.getString("USER_TEXT"),
                    rs.getString("USER_DESCRIPTION")
                )
            }
        }
    
    // Create
    final override fun insertIntoUsers(details: TesterDetails) {
        val sql = "INSERT INTO USERS (USER_TEXT, USER_DESCRIPTION) VALUES (?, ?)"
        val statementSetter = PreparedStatementSetter { ps: PreparedStatement ->
            run {
                ps.setString(1, details.userText)
                ps.setString(2, details.userDescription)
            }
        }
        jdbcTemplate.update(sql, statementSetter)
    }

    // Read - Get all records
    final override fun getAllUserRecords(): List<TesterDetails> {
        var sql = "SELECT USER_ID, USER_TEXT, USER_DESCRIPTION FROM USERS"
        return jdbcTemplate.query(sql, usersRowMapper)
    }

    // Read - Get one record 
    final override fun getUserRecord(userId: Long): TesterDetails? {
        val sql = "SELECT USER_ID, USER_TEXT, USER_DESCRIPTION FROM USERS WHERE USER_ID =?"
        return jdbcTemplate.queryForObject(sql, usersRowMapper, userId)
    }

    // Update
    final override fun updateUserRecord(userId: Long, userDescription: String) {
        val sql = "UPDATE USERS SET USER_DESCRIPTION =? WHERE USER_ID =?"
        val statementSetter = PreparedStatementSetter { ps: PreparedStatement ->
            run {
                ps.setString(1, userDescription)
                ps.setLong(2, userId)
            }
        }
        println(String.format("{%s} RECORDS UPDATED", jdbcTemplate.update(sql, statementSetter)))
    }

    // Delete
    final override fun deleteUserRecords() {
        // Delete all user records
        val deleteSql = "DELETE FROM USERS"
        jdbcTemplate.update(deleteSql)
        // Reset auto_increment sequence for pk user_id
        val alterSequenceSql = "ALTER TABLE USERS ALTER COLUMN USER_ID RESTART WITH 1"
        jdbcTemplate.update(alterSequenceSql)
    }
}