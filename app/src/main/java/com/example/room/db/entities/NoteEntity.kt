package com.example.room.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas")
class NoteEntity(title: String, content:String, favorite: Boolean) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "content")
    var content: String? = null

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

    init{
        this.title = title
        this.content = content
        this.favorite = favorite
    }
}