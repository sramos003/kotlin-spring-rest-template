package com.example.kotlinspring.repositories

import com.example.kotlinspring.dao_models.Users

/**
 * Below are included methods to perform the basic CRUD operations on an internal H2 database having its state
 * saved on application exit to ./database/cached_database.mv.db
 */
interface IUsersRepository {
    // Create
    fun insertIntoUsers(userDetails: Users)
    // Read - Get all records
    fun getAllUserRecords(): List<Users>
    // Read - Get one record 
    fun getUserRecord(userId: Long): Users?
    // Update
    fun updateUserRecord(userDetails: Users)
    // Delete
    fun deleteUserRecords()
}