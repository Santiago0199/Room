package com.example.room.ui.user.new_user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.UsersRepository
import com.example.room.db.NotesRoomDatabase
import com.example.room.db.dao.UserDao
import com.example.room.db.entities.NoteEntity
import com.example.room.db.entities.UserEntity
import kotlinx.coroutines.launch

class NewUserViewModel(application: Application): AndroidViewModel(application) {

    var userDao: UserDao
    var usersRepository: UsersRepository

    init {
        userDao = NotesRoomDatabase.getDatabase(application, viewModelScope).userDao()
        usersRepository = UsersRepository(userDao)
    }

    fun findUserByEmail(email: String): LiveData<UserEntity> {
        return usersRepository.findUserByEmail(email)
    }

    fun findUser(email: String, password: String): LiveData<UserEntity> {
        return usersRepository.findUser(email, password)
    }

    fun insert(user: UserEntity) = viewModelScope.launch{
        usersRepository.insert(user)
    }

}