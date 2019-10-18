package com.example.room.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.NotesRepository
import com.example.room.db.NotesRoomDatabase
import com.example.room.db.entities.NoteEntity
import com.example.room.db.entities.UserEntity
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository
    val allNotes: LiveData<List<NoteEntity>>
    val allFavorites: LiveData<List<NoteEntity>>

    init {
        val noteDao = NotesRoomDatabase.getDatabase(application, viewModelScope).noteDao()
        repository = NotesRepository(noteDao)
        allNotes = repository.allNotes
        allFavorites = repository.allFavorites
    }

    fun getByUser(idUser: Long): LiveData<List<NoteEntity>>{
        return repository.getByUser(idUser)
    }

    //viewModelScope lanza una subrutina
    fun insert(note: NoteEntity) = viewModelScope.launch {
        repository.insert(note)
    }

    fun delete(note: NoteEntity) = viewModelScope.launch {
        repository.delete(note)
    }

    fun update(note: NoteEntity) = viewModelScope.launch {
        repository.update(note)
    }

}