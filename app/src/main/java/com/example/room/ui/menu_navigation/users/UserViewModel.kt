package com.example.room.ui.menu_navigation.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.UsersRepository
import com.example.room.db.NotesRoomDatabase
import com.example.room.db.dao.UserDao
import com.example.room.db.entities.UserEntity

class UserViewModel(application: Application): AndroidViewModel(application) {

    var repository: UsersRepository
    var userDao: UserDao
    val allUsers: LiveData<List<UserEntity>>

    init {
        userDao = NotesRoomDatabase.getDatabase(application, viewModelScope).userDao()
        repository = UsersRepository(userDao)

        allUsers = repository.allUsers
    }

}