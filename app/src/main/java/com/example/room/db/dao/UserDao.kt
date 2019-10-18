package com.example.room.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room.db.entities.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    fun findUser(email: String, password: String):LiveData<UserEntity>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    fun findUserByEmail(email:String):LiveData<UserEntity>

    @Query("SELECT * FROM users ORDER BY email ASC")
    fun getAll():LiveData<List<UserEntity>>

}