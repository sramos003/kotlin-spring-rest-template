package com.example.kotlinspring.repositories.impl

import com.example.kotlinspring.dao_models.Users
import com.example.kotlinspring.repositories.IUsersRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.util.stream.Stream

@Repository
class UsersRepositoryImpl(private var jdbcTemplate: JdbcTemplate) : IUsersRepository {
    // Create
    final override fun insertIntoUsers(userDetails: Users) {
        jdbcTemplate.update(
            "INSERT INTO USERS (USER_NAME, USER_DESCRIPTION) VALUES (?, ?)",
            Users.getInsertStatementSetter(userDetails)
        )
    }

    // Read - Get all records
    final override fun getAllUserRecords(): List<Users> {
        return jdbcTemplate.query(
            "SELECT USER_ID, USER_NAME, USER_DESCRIPTION FROM USERS",
            Users.getRowMapper()
        )
    }

    // Read - Get one record 
    final override fun getUserRecord(userId: Long): Users? {
        return jdbcTemplate.queryForObject(
            "SELECT USER_ID, USER_NAME, USER_DESCRIPTION FROM USERS WHERE USER_ID =?",
            Users.getRowMapper(),
            userId
        )
    }

    // Update
    final override fun updateUserRecord(userDetails: Users) {
        println(
            String.format(
                "{%s} RECORDS UPDATED",
                jdbcTemplate.update(
                    "UPDATE USERS SET USER_DESCRIPTION =? WHERE USER_ID =?",
                    Users.getUpdateStatementSetter(userDetails)
                )
            )
        )
    }

    // Delete
    final override fun deleteUserRecords() {
        // Delete all user records then
        // Reset auto_increment sequence for pk user_id
        Stream.of(
            "DELETE FROM USERS",
            "ALTER TABLE USERS ALTER COLUMN USER_ID RESTART WITH 1"
        ).forEach { sql: String -> jdbcTemplate.update(sql) }
    }
}