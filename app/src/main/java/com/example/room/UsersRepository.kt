package com.example.room

import androidx.lifecycle.LiveData
import com.example.room.db.dao.UserDao
import com.example.room.db.entities.UserEntity

class UsersRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<UserEntity>> = userDao.getAll()

    fun findUserById(id: Long): LiveData<UserEntity>{
        return userDao.findUserById(id)
    }

    fun findUserByEmailPassword(email: String, password: String): LiveData<UserEntity> {
        return userDao.findUserByEmailPassword(email, password)
    }

    fun findUserByEmail(email:String): LiveData<UserEntity>{
        return userDao.findUserByEmail(email)
    }

    suspend fun insert(user: UserEntity){
        userDao.insert(user)
    }

    suspend fun delete(user:UserEntity){
        userDao.delete(user)
    }

    suspend fun deleteAll(){
        userDao.deleteAll()
    }

}