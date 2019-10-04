package com.example.room.ui.menu_navigation.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.NotesRepository
import com.example.room.db.NotesRoomDatabase
import com.example.room.db.entities.NoteEntity

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository
    val allFavorites: LiveData<List<NoteEntity>>

    init {
        val noteDao = NotesRoomDatabase.getDatabase(application, viewModelScope).noteDao()
        repository = NotesRepository(noteDao)
        allFavorites = repository.allFavorites
    }

}