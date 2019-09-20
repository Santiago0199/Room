package com.example.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room.db.dao.NoteDao
import com.example.room.db.entities.NoteEntity
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.room.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(NoteEntity::class), version = 1)
abstract class NotesRoomDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

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
                    .addCallback(NoteDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class NoteDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
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
    }
}