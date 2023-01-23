package com.example.kotlinspring.dao_models

import org.springframework.jdbc.core.PreparedStatementSetter
import org.springframework.jdbc.core.RowMapper
import java.sql.PreparedStatement
import java.sql.ResultSet

class Users {
    var userId: Long = 0L
    var userName: String
    var userDescription: String

    constructor(userId: Long, userText: String, userDescription: String) {
        this.userId = userId
        this.userName = userText
        this.userDescription = userDescription
    }
    
    constructor(userText: String, userDescription: String) {
        this.userName = userText
        this.userDescription = userDescription
    }
    
    // Overriding toString default.
    override fun toString(): String {
        return String.format("TESTER_DETAILS(USER_ID: %s, USER_TEXT: %s, USER_DESCRIPTION: %s)", userId, userName, userDescription)
    }
    
    companion object {
        fun getRowMapper(): RowMapper<Users> {
            return RowMapper { rs: ResultSet, rowNum: Int ->
                    run {
                        Users(
                            rs.getLong("USER_ID"),
                            rs.getString("USER_TEXT"),
                            rs.getString("USER_DESCRIPTION")
                        )
                    }
                }
        }
        
        fun getInsertStatementSetter(context: Users): PreparedStatementSetter {
            return PreparedStatementSetter { ps: PreparedStatement ->
                run {
                    ps.setString(1, context.userName)
                    ps.setString(2, context.userDescription)
                }
            }
        }
        
        fun getUpdateStatementSetter(context: Users): PreparedStatementSetter {
            return PreparedStatementSetter { ps: PreparedStatement ->
                run {
                    ps.setString(1, context.userDescription)
                    ps.setLong(2, context.userId)
                }
            }
        }
    }
}