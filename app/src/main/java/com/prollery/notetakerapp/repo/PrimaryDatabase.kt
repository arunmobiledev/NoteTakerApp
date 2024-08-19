package com.prollery.notetakerapp.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prollery.notetakerapp.BuildConfig
import com.prollery.notetakerapp.model.Note
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [Note::class], version = 2)
abstract class PrimaryDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {

        fun getDatabase(context: Context): PrimaryDatabase {
            val passphrase: ByteArray = SQLiteDatabase.getBytes("SimplePass123".toCharArray())
            val factory = SupportFactory(passphrase)
            return if (BuildConfig.DEBUG) {
                Room.databaseBuilder(
                    context.applicationContext,
                    PrimaryDatabase::class.java,
                    "primary_database.sqlite"
                ).build()
            } else {
                Room.databaseBuilder(
                    context.applicationContext,
                    PrimaryDatabase::class.java,
                    "secured_database.sqlite"
                ).openHelperFactory(factory).build()
            }
        }
    }

}