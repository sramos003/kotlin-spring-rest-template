package com.example.ktorrest.models


class TesterDetails(
    var userId: Long,
    var userText: String?,
    var userDescription: String?
) {
    override fun toString(): String {
        return String.format("USER_ID: %s, USER_TEXT: %s, USER_DESCRIPTION: %s", userId, userText, userDescription)
    }
}