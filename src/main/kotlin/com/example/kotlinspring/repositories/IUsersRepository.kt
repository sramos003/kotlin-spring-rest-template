package com.example.kotlinspring.repositories

import com.example.kotlinspring.dao_models.Users
import org.springframework.dao.DataAccessException

/**
 * Below are included methods to perform the basic CRUD operations on an internal H2 database having its state
 * saved on application exit to ./database/cached_database.mv.db
 */
interface IUsersRepository {
    
    // Create
    @Throws(DataAccessException::class)
    fun insertIntoUsers(userDetails: Users)
    
    // Read - Get all records
    @Throws(DataAccessException::class)
    fun getAllUserRecords(): List<Users>
    
    // Read - Get one record 
    @Throws(DataAccessException::class)
    fun getUserRecord(userId: Long): Users?
    
    // Update
    @Throws(DataAccessException::class)
    fun updateUserRecord(userDetails: Users)
    
    // Delete
    @Throws(DataAccessException::class)
    fun deleteUserRecords()
}