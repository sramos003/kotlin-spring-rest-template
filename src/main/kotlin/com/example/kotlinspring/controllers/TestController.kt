package com.example.kotlinspring.controllers

import com.example.kotlinspring.dao_models.Users
import com.example.kotlinspring.repositories.IUsersRepository
import com.thedeanda.lorem.LoremIpsum
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import kotlin.collections.ArrayList

@RestController
@RequestMapping(value = ["/test-controller"])
class TestController(private var usersRepository: IUsersRepository) {

    @GetMapping(value = ["/health-check"], produces = [MediaType.TEXT_HTML_VALUE])
    fun healthCheckRoute(): ResponseEntity<String> {
        return ResponseEntity.ok("My first kotlin API")
    }

    // Create
    @PostMapping(value = ["/save-new-user/{userName}"], produces = [MediaType.TEXT_HTML_VALUE])
    fun saveNewUser(@PathVariable userName: String): ResponseEntity<String> {
        usersRepository.insertIntoUsers(Users(userName.uppercase(), LoremIpsum.getInstance().getWords(5)))
        return ResponseEntity.ok(String.format("SAVED NEW USER {%s} TO DB", userName.uppercase()))
    }

    // Read - All
    @GetMapping(value = ["/get-all-users"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllUsers(): List<Users> {
        return ArrayList(usersRepository.getAllUserRecords())
    }

    // Read - One
    @GetMapping(value = ["/get-user/{userId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(@PathVariable userId: Long): Users? {
        return usersRepository.getUserRecord(userId)
    }

    // Update
    @PostMapping(value = ["/update-user-description/{userId}"], produces = [MediaType.TEXT_HTML_VALUE])
    fun updateUser(@PathVariable userId: Long, @RequestBody userDescription: String): ResponseEntity<String> {
        usersRepository.updateUserRecord(userId, userDescription)
        return ResponseEntity.ok(String.format("UPDATED USER_ID %s", userId))
    }

    // Delete
    @GetMapping(value = ["/delete-all-users"], produces = [MediaType.TEXT_HTML_VALUE])
    fun deleteAllUsers(): ResponseEntity<String> {
        usersRepository.deleteUserRecords()
        return ResponseEntity.ok("TABLE CLEARED SUCCESSFULLY")

    }
}