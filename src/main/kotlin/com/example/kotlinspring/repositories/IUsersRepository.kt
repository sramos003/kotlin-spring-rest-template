package com.example.kotlinspring.repositories

import com.example.kotlinspring.models.TesterDetails

interface IUsersRepository {
    /**
     * Below are included methods to perform the basic CRUD operations on an internal H2 database having its state
     * saved on application exit to ./database/cached_database.mv.db
     */
    
    // Create
    fun insertIntoUsers(details: TesterDetails)
    // Read - Get all records
    fun getAllUserRecords(): List<TesterDetails>
    // Read - Get one record 
    fun getUserRecord(userId: Long): TesterDetails?
    // Update
    fun updateUserRecord(userId: Long, userDescription: String)
    // Delete
    fun deleteUserRecords()
}