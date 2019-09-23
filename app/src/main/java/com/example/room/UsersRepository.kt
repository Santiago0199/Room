package com.example.room

import com.example.room.db.dao.UserDao
import com.example.room.db.entities.UserEntity

class UsersRepository(private val userDao: UserDao) {

    fun findUser(email: String, password: String){
        userDao.findUser(email, password)
    }

    suspend fun insert(user: UserEntity){
        userDao.insert(user)
    }

}