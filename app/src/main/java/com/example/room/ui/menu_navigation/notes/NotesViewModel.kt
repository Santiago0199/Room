package com.example.room.ui.menu_navigation.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.NotesRepository
import com.example.room.db.NotesRoomDatabase
import com.example.room.db.entities.NoteEntity
import com.example.room.db.entities.UserEntity

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository
    val allNotes: LiveData<List<NoteEntity>>

    init {
        val noteDao = NotesRoomDatabase.getDatabase(application, viewModelScope).noteDao()
        repository = NotesRepository(noteDao)
        allNotes = repository.allNotes
    }

    fun getByUser(idUser: Long): LiveData<List<NoteEntity>>{
        return repository.getByUser(idUser)
    }


}