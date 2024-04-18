package com.prollery.notetakerapp.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prollery.notetakerapp.model.Note

@Database(entities = [Note::class], version = 2)
abstract class PrimaryDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE: PrimaryDatabase? = null

        fun getDatabase(context: Context): PrimaryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PrimaryDatabase::class.java,
                    "primary_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}