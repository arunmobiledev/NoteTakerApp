package com.prollery.notetakerapp.repo

import androidx.lifecycle.LiveData
import com.prollery.notetakerapp.model.Note

class NotesRepo(private val noteDao: NoteDao) {

    var allNotes : LiveData<List<Note>> = noteDao.fetchAllNotes()

    suspend fun createNote(note: Note) {
        noteDao.createNote(note)
    }

    suspend fun deleteNote(id: Long) {
        noteDao.deleteNote(id)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    fun fetchAllNotes() {
        allNotes = noteDao.fetchAllNotes()
    }

}