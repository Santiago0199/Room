package com.example.room.db

import android.content.Context
import androidx.room.*
import com.example.room.db.dao.NoteDao
import com.example.room.db.entities.NoteEntity
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.db.dao.UserDao
import com.example.room.db.entities.UserEntity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.Serializable

@Database(entities = arrayOf(UserEntity::class, NoteEntity::class), version = 1)
abstract class NotesRoomDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun userDao(): UserDao

    companion object{

        @Volatile
        private var INSTANCE: NotesRoomDatabase? = null

        /**
         * Crea la instancia de la BD
         * @param scope variable utlizada para lanzar una subrutina
         */
        fun getDatabase(context: Context, scope: CoroutineScope): NotesRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, NotesRoomDatabase::class.java, "notas")
                    //.addCallback(NoteDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    /*private class NoteDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        /**
         * Inicia una rutina en el IO Dispatcher.
         */
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    //Consultas al iniciar la aplicacion
                    //var wordDao = database.noteDao()
                    //wordDao.deleteAll()
                }
            }
        }
    }*/

    class UserTypeConverter: Serializable {

        @TypeConverter
        fun convertToString(user: UserEntity): String? {
            var gson = Gson()
            var str = gson.toJson(user, UserEntity::class.java)
            return str
        }

        @TypeConverter
        fun convertToUser(str: String): UserEntity {
            var gson = Gson()
            var user = gson.fromJson(str, UserEntity::class.java)
            return user
        }
    }
}