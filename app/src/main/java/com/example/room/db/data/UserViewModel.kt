package com.example.room.db.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.db.NotesRoomDatabase
import com.example.room.db.dao.UserDao
import com.example.room.db.entities.UserEntity
import kotlinx.coroutines.launch
import android.widget.Toast
import android.app.Activity
import android.content.Intent
import com.example.room.common.MyApp
import android.os.AsyncTask
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class UserViewModel(application: Application): AndroidViewModel(application) {

    var repository: UsersRepository
    var userDao: UserDao
    val allUsers: LiveData<List<UserEntity>>

    init {
        userDao = NotesRoomDatabase.getDatabase(application).userDao()
        repository = UsersRepository(userDao)

        allUsers = repository.allUsers
    }

    fun findUserById(id: Long): UserEntity {
        return repository.findUserById(id)
    }

    fun findUserByEmail(email: String) {
        repository.findUserByEmail(email)
    }

    fun findUserByEmailPassword(email: String, password: String) {
        repository.findUserByEmailPassword(email, password)
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