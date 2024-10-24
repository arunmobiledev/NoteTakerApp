package com.prollery.notetakerapp.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import com.prollery.notetakerapp.model.Note

@Dao
interface NoteDao {

    @RawQuery
    suspend fun checkpoint(supportSQLiteQuery: SupportSQLiteQuery): Int

    @Insert
    suspend fun createNote(note : Note) : Long

    @Query("delete from tblNotes where id = :id")
    suspend fun deleteNote(id : Long)

    @Update
    suspend fun updateNote(note : Note)

    @Query("select * from tblNotes")
    fun fetchAllNotes() : LiveData<List<Note>>

}