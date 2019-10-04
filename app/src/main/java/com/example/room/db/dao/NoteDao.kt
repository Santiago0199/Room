package com.example.room.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room.db.entities.NoteEntity
import com.example.room.db.entities.UserEntity

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY upper(title) ASC")
    fun getAll(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE favorite = '1'")
    fun getFavorites(): LiveData<List<NoteEntity>>

    @Query("SELECT n.id, n.title, n.content, n.favorite, n.user_id FROM notes n INNER JOIN users u ON n.user_id = u.id WHERE u.id = :idUser")
    fun getByUser(idUser: Long):LiveData<List<NoteEntity>>

    @Query("DELETE FROM notes WHERE id = :idNota")
    fun deleteById(idNota: Long)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()
}