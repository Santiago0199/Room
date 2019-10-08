package com.example.room.db.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "users", indices = arrayOf(Index(value = ["id"], unique = true)))
class UserEntity(email: String, password: String): Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "password")
    var password: String = ""

    init {
        this.email = email
        this.password = password
    }

}