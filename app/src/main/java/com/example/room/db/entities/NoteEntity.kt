package com.example.room.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas")
class NoteEntity(title: String, content:String, favorite: Boolean) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var title: String? = null
    var content: String? = null
    var favorite: Boolean = false

    init{
        this.title = title
        this.content = content
        this.favorite = favorite
    }

}