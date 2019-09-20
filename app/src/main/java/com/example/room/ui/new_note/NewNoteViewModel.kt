package com.example.room.ui.new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.NotesRepository
import com.example.room.db.NotesRoomDatabase
import com.example.room.db.entities.NoteEntity
import kotlinx.coroutines.launch

class NewNoteViewModel(application: Application) : AndroidViewModel(application) {

    var repository: NotesRepository

    init {
        var noteDato = NotesRoomDatabase.getDatabase(application, viewModelScope).noteDao()
        repository = NotesRepository(noteDato)
    }

    //viewModelScope lanza una subrutina
    fun insert(note: NoteEntity) = viewModelScope.launch {
        repository.insert(note)
    }

}
