package com.example.room.ui.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.NotesRepository
import com.example.room.db.NotesRoomDatabase
import com.example.room.db.entities.NoteEntity
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository
    val allNotes: LiveData<List<NoteEntity>>

    init {
        val noteDao = NotesRoomDatabase.getDatabase(application, viewModelScope).noteDao()
        repository = NotesRepository(noteDao)
        allNotes = repository.allNotes
    }
}