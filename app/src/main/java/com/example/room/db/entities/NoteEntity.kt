package com.example.room.db.entities

import androidx.room.*
import com.example.room.db.NotesRoomDatabase
import java.io.Serializable

@Entity (tableName = "notes",
    indices = arrayOf(Index(value = ["id"], unique = true), Index(value = ["user_id"])),
    foreignKeys = arrayOf(
        ForeignKey (entity = UserEntity::class,
                    parentColumns = arrayOf ("id"),
                    childColumns = arrayOf ("user_id"),
                    onDelete = ForeignKey.CASCADE,
                    onUpdate = ForeignKey.CASCADE)))

//@TypeConverters(NotesRoomDatabase.UserTypeConverter::class)
class NoteEntity(title: String, content:String, favorite: Boolean, user_id: Long): Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "content")
    var content: String? = null

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

    @ColumnInfo(name = "user_id")
    var user_id: Long
    //@Embedded var user_id: UserEntity

    init{
        this.title = title
        this.content = content
        this.favorite = favorite
        this.user_id = user_id
    }
}