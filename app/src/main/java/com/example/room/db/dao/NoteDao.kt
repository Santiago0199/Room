package com.example.room.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room.db.entities.NoteEntity

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Query("SELECT * FROM notas ORDER BY upper(title) ASC")
    fun getAll(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notas WHERE favorite = '1'")
    fun getFavorites(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notas WHERE id = :idNota")
    fun getById(idNota: Int):NoteEntity

    @Query("DELETE FROM notas WHERE id = :idNota")
    fun deleteById(idNota: Int)

    @Query("DELETE FROM notas")
    suspend fun deleteAll()
}