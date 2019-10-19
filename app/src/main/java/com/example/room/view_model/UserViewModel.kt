package com.example.room.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.UsersRepository
import com.example.room.db.NotesRoomDatabase
import com.example.room.db.dao.UserDao
import com.example.room.db.entities.UserEntity
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    var repository: UsersRepository
    var userDao: UserDao
    val allUsers: LiveData<List<UserEntity>>

    init {
        userDao = NotesRoomDatabase.getDatabase(application, viewModelScope).userDao()
        repository = UsersRepository(userDao)

        allUsers = repository.allUsers
    }

    fun findUserById(id: Long): LiveData<UserEntity> {
        return repository.findUserById(id)
    }

    fun findUserByEmail(email: String): LiveData<UserEntity> {
        return repository.findUserByEmail(email)
    }

    fun findUserByEmailPassword(email: String, password: String): LiveData<UserEntity> {
        return repository.findUserByEmailPassword(email, password)
    }

    fun insert(user: UserEntity) = viewModelScope.launch {
        repository.insert(user)
    }

    fun delete(user:UserEntity) = viewModelScope.launch {
        repository.delete(user)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

}