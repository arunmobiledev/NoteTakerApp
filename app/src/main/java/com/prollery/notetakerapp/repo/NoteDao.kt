package com.prollery.notetakerapp.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.prollery.notetakerapp.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun createNote(note : Note)

    @Query("delete from tblNotes where id = :id")
    suspend fun deleteNote(id : Long)

    @Update
    suspend fun updateNote(note : Note)

    @Query("select * from tblNotes")
    fun fetchAllNotes() : LiveData<List<Note>>

}