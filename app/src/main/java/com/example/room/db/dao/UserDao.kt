package com.example.room.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.room.db.entities.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    fun findUser(email: String, password: String):LiveData<UserEntity>

    @Query("SELECT * FROM users ORDER BY email ASC")
    fun getAll():LiveData<List<UserEntity>>

}