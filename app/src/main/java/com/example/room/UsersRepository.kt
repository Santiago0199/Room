package com.example.room

import androidx.lifecycle.LiveData
import com.example.room.db.dao.UserDao
import com.example.room.db.entities.UserEntity

class UsersRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<UserEntity>> = userDao.getAll()

    fun findUser(email: String, password: String): LiveData<UserEntity> {
        return userDao.findUser(email, password)
    }

    fun findUserByEmail(email:String): LiveData<UserEntity>{
        return userDao.findUserByEmail(email)
    }

    suspend fun insert(user: UserEntity){
        userDao.insert(user)
    }

}