package com.example.kotlinspring.dao_models

class Users {
    var userId: Long = 0L
    var userText: String
    var userDescription: String

    constructor(userId: Long, userText: String, userDescription: String) {
        this.userId = userId
        this.userText = userText
        this.userDescription = userDescription
    }
    
    constructor(userText: String, userDescription: String) {
        this.userText = userText
        this.userDescription = userDescription
    }
    
    // Overriding toString default.
    override fun toString(): String {
        return String.format("TESTER_DETAILS(USER_ID: %s, USER_TEXT: %s, USER_DESCRIPTION: %s)", userId, userText, userDescription)
    }
}