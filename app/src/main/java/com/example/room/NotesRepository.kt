package com.example.room

import androidx.lifecycle.LiveData
import com.example.room.db.dao.NoteDao
import com.example.room.db.entities.NoteEntity

class NotesRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<NoteEntity>> = noteDao.getAll()
    val allFavorites: LiveData<List<NoteEntity>> = noteDao.getFavorites()

    suspend fun insert(note: NoteEntity) {
        noteDao.insert(note)
    }
}