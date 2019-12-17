package com.example.room.db.data

import androidx.lifecycle.LiveData
import com.example.room.db.dao.NoteDao
import com.example.room.db.entities.NoteEntity
import com.example.room.db.entities.UserEntity

class NotesRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<NoteEntity>> = noteDao.getAll()
    val allFavorites: LiveData<List<NoteEntity>> = noteDao.getFavorites()

    fun getByUser(idUser: Long): LiveData<List<NoteEntity>>{
        return noteDao.getByUser(idUser)
    }

    fun getFavoritesByUser(idUser: Long): LiveData<List<NoteEntity>>{
        return noteDao.getByUser(idUser)
    }

    suspend fun insert(note: NoteEntity) {
        noteDao.insert(note)
    }

    suspend fun delete(note: NoteEntity){
        noteDao.delete(note)
    }

    suspend fun update(note: NoteEntity){
        noteDao.update(note)
    }
}