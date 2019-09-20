package com.example.room.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
class UserEntity(email: String, pasword: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    var email: String = ""
    var password: String = ""

    init {
        this.email = email
        this.password = password
    }

}